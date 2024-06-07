package com.shopping.cotizacion.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shopping.cotizacion.modelo.DetalleCotizacion;

public interface PrimaryRepositoryDetailCotiz extends JpaRepository<DetalleCotizacion, Integer> {

	 @Query(value="SELECT * FROM wp_detalle_cotizacion WHERE id_cotizacion=:id", nativeQuery=true)
	    List<DetalleCotizacion> obtenerDetalleCotizacion(@Param("id")  int idCotizacion);

}
