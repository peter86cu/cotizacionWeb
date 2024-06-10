package com.shopping.cotizacion.modelo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wp_cotizacion")
public class Cotizacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	private int id;
	private String estado;
	private Date fecha_creacion;
	private String mensaje;
	private int soporte;
	private String cliente_id;
	private String producto_id;
	private String app_id;
	private String apis_id;
	private String planes_hosting_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public int getSoporte() {
		return soporte;
	}
	public void setSoporte(int soporte) {
		this.soporte = soporte;
	}
	public String getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(String cliente_id) {
		this.cliente_id = cliente_id;
	}
	
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getApis_id() {
		return apis_id;
	}
	public void setApis_id(String apis_id) {
		this.apis_id = apis_id;
	}
	public String getPlanes_hosting_id() {
		return planes_hosting_id;
	}
	public void setPlanes_hosting_id(String planes_hosting_id) {
		this.planes_hosting_id = planes_hosting_id;
	}
	public Cotizacion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getProducto_id() {
		return producto_id;
	}
	public void setProducto_id(String producto_id) {
		this.producto_id = producto_id;
	}

	
	
	
}
