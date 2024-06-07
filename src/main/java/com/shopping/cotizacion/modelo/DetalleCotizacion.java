package com.shopping.cotizacion.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wp_detalle_cotizacion")
public class DetalleCotizacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	private int id;
	private int id_cotizacion;
	private int id_seccion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_cotizacion() {
		return id_cotizacion;
	}
	public void setId_cotizacion(int id_cotizacion) {
		this.id_cotizacion = id_cotizacion;
	}
	public int getId_seccion() {
		return id_seccion;
	}
	public void setId_seccion(int id_seccion) {
		this.id_seccion = id_seccion;
	}
	
	
	
}
