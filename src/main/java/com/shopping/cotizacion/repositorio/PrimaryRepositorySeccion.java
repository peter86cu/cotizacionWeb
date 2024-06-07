package com.shopping.cotizacion.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.cotizacion.modelo.DetalleCotizacion;
import com.shopping.cotizacion.modelo.Secciones;

public interface PrimaryRepositorySeccion extends JpaRepository<Secciones, Integer> {


}
