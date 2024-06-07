package com.shopping.cotizacion.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;


import com.shopping.cotizacion.service.CotizacionService;


@RestController
public class CotizacionController {

	@Autowired
	CotizacionService service;
	
	@GetMapping(value = "cotizacion/pendientes", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> obtenerCotizacionesWebPendentes() {
		return service.obtenerCotizacionWeb();

	}

}
