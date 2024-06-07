package com.shopping.cotizacion.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shopping.cotizacion.modelo.Cotizacion;


public interface PrimaryRepositoryCotizacion extends JpaRepository<Cotizacion, Integer> {
	
	 @Query(value="SELECT * FROM wp_cotizacion WHERE estado='Pendiente'", nativeQuery=true)
	    List<Cotizacion> obtenerCotizacionPendientesWeb();

}
