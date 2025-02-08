package com.shopping.dashboard.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.shopping.dashboard.repositorio.VisitantesJPA;
import com.shopping.dashboard.vo.ErrorState;
import com.shopping.dashboard.vo.VisitantesLog;

@Service
public class CotizacionServiceImpl implements CotizacionService {

	public String stock;

	@Autowired
	VisitantesJPA visitantesJPA;
	
	

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
	public ResponseEntity<String> obtenerVisitantesMensuales() {
		
		try {
		Iterator<Object> lst = visitantesJPA.visitantesMensuales().iterator();
		List<VisitantesLog> lstVisitantes= new ArrayList<VisitantesLog>();
		if(lst != null && lst.hasNext()) {
			while( lst.hasNext()) {
				Object[] objArray = (Object[]) lst.next();
				VisitantesLog response= new VisitantesLog();
				   response.setCantidad(Integer.parseInt(objArray[0].toString()) );
				   					  
				   response.setMes(objArray[1].toString());
				   response.setAnio(Integer.parseInt(objArray[2].toString()));
				  
				   lstVisitantes.add(response);
			}
		
	        
	     // Ordenar la lista por fecha en orden descendente usando una expresión lambda
			//Collections.sort(lstVisitantes, (r1, r2) -> r1.getFecha().compareTo(r2.getFecha()));


	        
			return new ResponseEntity<String>(new Gson().toJson(lstVisitantes),HttpStatus.OK);
		}else {
			error.setCode(90002);
			error.setMenssage("No hay registros de prefacturas.");
			return new ResponseEntity<String>(new Gson().toJson(error),HttpStatus.BAD_GATEWAY);
		}
	} catch (Exception e) {
		error.setCode(90020);
		error.setMenssage(e.getCause().getMessage());
		return new ResponseEntity<String>(new Gson().toJson(error),HttpStatus.NOT_ACCEPTABLE);
	}
	}

}
