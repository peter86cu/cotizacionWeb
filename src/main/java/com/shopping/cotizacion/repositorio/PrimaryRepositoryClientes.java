package com.shopping.cotizacion.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shopping.cotizacion.modelo.Clientes;
import com.shopping.cotizacion.modelo.Cotizacion;

public interface PrimaryRepositoryClientes extends JpaRepository<Clientes, Integer> {


}
