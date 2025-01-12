package com.shopping.cotizacion.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.shopping.cotizacion.modelo.Cliente;
import com.shopping.cotizacion.modelo.Clientes;
import com.shopping.cotizacion.modelo.Cotizacion;
import com.shopping.cotizacion.modelo.DetalleCotizacion;
import com.shopping.cotizacion.modelo.Prefactura;
import com.shopping.cotizacion.modelo.PrefacturaDetalle;
import com.shopping.cotizacion.modelo.Producto;
import com.shopping.cotizacion.modelo.Secciones;
import com.shopping.cotizacion.repositorio.PrimaryRepositoryClientes;
import com.shopping.cotizacion.repositorio.PrimaryRepositoryCotizacion;
import com.shopping.cotizacion.repositorio.PrimaryRepositoryDetailCotiz;
import com.shopping.cotizacion.repositorio.PrimaryRepositorySeccion;
import com.shopping.cotizacion.vo.ErrorState;

@Service
public class CotizacionServiceImpl implements CotizacionService {

	public String stock;

	@Autowired
	PrimaryRepositoryClientes primaryRepositoryCliente;
	
	@Autowired
	PrimaryRepositoryCotizacion primaryRepositoryCotizacion;
	
	@Autowired
	PrimaryRepositoryDetailCotiz primaryRepositoryDetailCotiz;
	
	@Autowired
	PrimaryRepositorySeccion primaryRepositorySeccion;

	ObjectWriter ow = (new ObjectMapper()).writer().withDefaultPrettyPrinter();
	private boolean desarrollo = true;

	ErrorState error = new ErrorState();

	@Autowired
	RestTemplate restTemplate;

	void cargarServer() throws IOException {
		Properties p = new Properties();

		try {
			URL url = this.getClass().getClassLoader().getResource("application.properties");
			if (url == null) {
				throw new IllegalArgumentException("application.properties" + " is not found 1");
			} else {
				InputStream propertiesStream = url.openStream();
				p.load(propertiesStream);
				propertiesStream.close();
				this.stock = p.getProperty("server.stock");

			}
		} catch (FileNotFoundException var3) {
			System.err.println(var3.getMessage());
		}

	}

	public CotizacionServiceImpl() {
		try {
			if (desarrollo) {
				stock = "http://localhost:8082";

			} else {
				cargarServer();
			}
		} catch (IOException var2) {
			System.err.println(var2.getMessage());
		}

	}

	@Override
	public ResponseEntity<String> obtenerCotizacionWeb() {
		try {

			List<Cotizacion> cotizacion = primaryRepositoryCotizacion.obtenerCotizacionPendientesWeb();
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat formatoSinHora = new SimpleDateFormat("yyyy-MM-dd");

			if (!cotizacion.isEmpty()) {
				for (Cotizacion cotizacion2 : cotizacion) {
					//String idCliente = UUID.randomUUID().toString();
					Clientes cliente = primaryRepositoryCliente.obtenerClienteId(cotizacion2.getCliente_id());
					if (cliente != null) {
						ResponseEntity<String> responseClient= null;
						boolean existe=false;
						if(cliente.getEstado().equalsIgnoreCase("Pendiente")){
							Cliente client = new Cliente();
							client.setIdCliente(cliente.getId());
							client.setNombres(cliente.getNombre());
							client.setTelefono(cliente.getTelefono());
							client.setEmail(cliente.getCorreo());
							client.setIdTipoCliente(5);
							client.setDireccion("S/D");
							client.setNroDocumento("S/N");
							client.setFechaNacimiento("0000-00-00");
							client.setFechaAlta(formato.format(calendar.getTime()));
							
							String urlClient = this.stock + "/parametros/cliente/add";
							HttpHeaders headersClient = new HttpHeaders();
							HttpEntity<Cliente> requestEntity = new HttpEntity<>(client, headersClient);
							URI uri = new URI(urlClient);
							responseClient = restTemplate.exchange(uri, HttpMethod.POST,
									requestEntity, String.class);
							if(responseClient.getStatusCodeValue() == 200) {
								// Actualizo el cliente
								//cliente.setId(idCliente);
								cliente.setEstado("Agregado");
								primaryRepositoryCliente.save(cliente);
								existe=true;
							}
								
						}else if(cliente.getEstado().equalsIgnoreCase("Agregado")){
							existe=true;
							//idCliente= cliente.getId();
						}
						

						if (existe || responseClient.getStatusCodeValue() == 200 ) {

							Prefactura pref = new Prefactura();
							pref.setId_prefactura(UUID.randomUUID().toString());
							pref.setEstado(7);
							pref.setFecha_hora(formatoSinHora.format(calendar.getTime()));
							pref.setFecha_hora_creado(formato.format(calendar.getTime()));
							pref.setId_cliente(cliente.getId());
							pref.setId_cotizacion_producto(cotizacion2.getId());
							pref.setId_moneda(1);
							pref.setId_plazo(1);
							pref.setId_usuario("8fe25c3e-a8c8-48a5-943e-b0e94cd11db6");
							int min = 1;
					        int max = 200000;
					        int randomNumber = (int) (Math.random() * max) + min;
							pref.setCod_factura("PREF-"+ randomNumber);

							String urlAdd = this.stock + "/prefactura/add";
							HttpHeaders headersAdd = new HttpHeaders();
							HttpEntity<Prefactura> requestEntityPr = new HttpEntity<>(pref, headersAdd);
							URI uriAdd = new URI(urlAdd);
							ResponseEntity<String> responseAdd = restTemplate.exchange(uriAdd, HttpMethod.POST,
									requestEntityPr, String.class);

							if (responseAdd.getStatusCodeValue() == 200) {
								
								

								PrefacturaDetalle detail = new PrefacturaDetalle();
								detail.setId_prefactura(pref.getId_prefactura());
								detail.setCantidad(1);
								detail.setId_moneda(1);
								detail.setId_prefactura(pref.getId_prefactura());
								
								if (cotizacion2.getProducto_id() != null)
									detail.setId_producto(cotizacion2.getProducto_id());
								if (cotizacion2.getApis_id() != null)
									detail.setId_producto(cotizacion2.getApis_id());
								if (cotizacion2.getApp_id() != null)
									detail.setId_producto(cotizacion2.getApp_id());
								if (cotizacion2.getPlanes_hosting_id() != null)
									detail.setId_producto(cotizacion2.getPlanes_hosting_id());

								Producto prod = new Producto();

								String urlProd = this.stock + "/productos/obtener-codigo?codigo="
										+ detail.getId_producto();

								URI uriProd = new URI(urlProd);
								ResponseEntity<Producto> responseProducto = restTemplate.exchange(uriProd,
										HttpMethod.GET, null, Producto.class);
								
								if (responseProducto.getStatusCodeValue() == 200) {
									prod = responseProducto.getBody();
									detail.setImporte(prod.getPrecioventa());
									detail.setId_producto(prod.getId());
									detail.setId_producto(prod.getId());
									
									
								} else {
									detail.setImporte(0);
								}

								String urlAddD = this.stock + "/prefactura/detalle";
								HttpHeaders headersAddD = new HttpHeaders();
								HttpEntity<PrefacturaDetalle> requestEntityPrD = new HttpEntity<>(detail, headersAddD);
								URI uriAddD = new URI(urlAddD);
								ResponseEntity<String> responseAddD = restTemplate.exchange(uriAddD, HttpMethod.POST,
										requestEntityPrD, String.class);

								
								//Valido si tiene soporte 
								if(cotizacion2.getSoporte()==1) {
									
									PrefacturaDetalle detailSop = new PrefacturaDetalle();
									detailSop.setId_prefactura(pref.getId_prefactura());
									detailSop.setCantidad(1);
									detailSop.setId_moneda(1);
									detailSop.setId_prefactura(pref.getId_prefactura());
									
									
									Producto prodSer = new Producto();

									String urlServ = this.stock + "/productos/obtener-codigo?codigo=SOP001";

									URI urlSoporte = new URI(urlServ);
									ResponseEntity<Producto> responseSoporte = restTemplate.exchange(urlSoporte,
											HttpMethod.GET, null, Producto.class);
									
									if (responseSoporte.getStatusCodeValue() == 200) {
										prodSer = responseSoporte.getBody();
										detailSop.setImporte(prodSer.getPrecioventa());
										detailSop.setId_producto(prodSer.getId());																
										
									}

									String urlAddS = this.stock + "/prefactura/detalle";
									HttpEntity<PrefacturaDetalle> requestEntityServ = new HttpEntity<>(detailSop, null);
									URI uriAddS = new URI(urlAddS);
									ResponseEntity<String> responseAddS= restTemplate.exchange(uriAddS, HttpMethod.POST,
											requestEntityServ, String.class);
									
								}
								
								//Cargo el detalle de cotizaciones
								List<DetalleCotizacion> lstDetailCot= primaryRepositoryDetailCotiz.obtenerDetalleCotizacion(cotizacion2.getId()).stream().toList();
								if(!lstDetailCot.isEmpty()) {
									for (DetalleCotizacion cotizacion3 : lstDetailCot) {
										
										if (cotizacion2.getProducto_id() != null) {
											Secciones seccion = new Secciones();
											seccion= primaryRepositorySeccion.findById(cotizacion3.getId_seccion()).get();
											guardarDetalleCotizacion(pref,seccion.getCod_producto());
										}
										if (cotizacion2.getApis_id() != null) {
											
										}
											
									}							
									
								}
								
								
								if (responseAddD.getStatusCodeValue() == 200) {
									cotizacion2.setEstado("En evaluación");
									primaryRepositoryCotizacion.save(cotizacion2);
								} else {
									error.setCode(4002);
									error.setMenssage("Error actua;izando el estado de la cotizacion de la Web");
									return new ResponseEntity<String>(new Gson().toJson(error), HttpStatus.BAD_REQUEST);

								}

							} else {

								error.setCode(4002);
								error.setMenssage("Error creando la prefactura desde una cotizacion de la Web");
								return new ResponseEntity<String>(new Gson().toJson(error), HttpStatus.BAD_REQUEST);
							}

						} else {
							error.setCode(4002);
							error.setMenssage("Error guardando al cliente obtenido desde la Web");
							return new ResponseEntity<String>(new Gson().toJson(error), HttpStatus.BAD_REQUEST);
						}
					}else {
						error.setCode(4002);
						error.setMenssage("No se encontro el cliente");
						return new ResponseEntity<String>(new Gson().toJson(error), HttpStatus.BAD_REQUEST);	
					}
				}
			}else {
				return new ResponseEntity<String>("No se existen cotizaciones en la web.", HttpStatus.OK);

			}

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);

		}
		return null;

	}
	
	
	
	private boolean guardarDetalleCotizacion(Prefactura pref, String codigo) {
		boolean result=false;
		PrefacturaDetalle detailSop = new PrefacturaDetalle();
		detailSop.setId_prefactura(pref.getId_prefactura());
		detailSop.setCantidad(1);
		detailSop.setId_moneda(1);
		detailSop.setId_prefactura(pref.getId_prefactura());
		
		
		Producto prodSer = new Producto();

		String urlServ = this.stock + "/productos/obtener-codigo?codigo="+codigo;

		ResponseEntity<Producto> responseSoporte = restTemplate.exchange(urlServ,HttpMethod.GET, null, Producto.class);
		
		if (responseSoporte.getStatusCodeValue() == 200) {
			prodSer = responseSoporte.getBody();
			detailSop.setImporte(prodSer.getPrecioventa());
			detailSop.setId_producto(prodSer.getId());																
			
		}

		String urlAddS = this.stock + "/prefactura/detalle";
		HttpEntity<PrefacturaDetalle> requestEntityServ = new HttpEntity<>(detailSop, null);
		
		ResponseEntity<String> responseAddS= restTemplate.exchange(urlAddS, HttpMethod.POST,
				requestEntityServ, String.class);
		
		if (responseAddS.getStatusCodeValue() == 200) {
			result=true;															
			
		}
		
		return result;
	}

}
