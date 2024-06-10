package com.shopping.cotizacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.cotizacion.modelo.Clientes;

public interface PrimaryRepositoryClientes extends JpaRepository<Clientes, String> {


}
