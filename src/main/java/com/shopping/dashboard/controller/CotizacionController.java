package com.shopping.dashboard.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.shopping.dashboard.service.CotizacionService;


@RestController
public class CotizacionController {

	@Autowired
	CotizacionService service;
	
	@GetMapping(value = "dashboard/visitantes", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> obtenerVisitantesPorMeses() {
		return service.obtenerVisitantesMensuales();

	}

}
