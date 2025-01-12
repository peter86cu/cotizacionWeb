package com.shopping.cotizacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shopping.cotizacion.modelo.Clientes;

public interface PrimaryRepositoryClientes extends JpaRepository<Clientes, String> {

	
    @Query(value="SELECT * from wp_cliente  WHERE id =:id", nativeQuery=true)
	Clientes obtenerClienteId(String id);
	

}
