package com.shopping.cotizacion.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wp_seccion")
public class Secciones implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	private int id;
	private String nombre;
	private double precio;
	private String cod_producto;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getCod_producto() {
		return cod_producto;
	}
	public void setCod_producto(String cod_producto) {
		this.cod_producto = cod_producto;
	}
	
	
	
	
}
