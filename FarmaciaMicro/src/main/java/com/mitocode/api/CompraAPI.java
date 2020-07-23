package com.mitocode.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.Compra;
import com.mitocode.service.ICompraService;

@RestController
@RequestMapping("/compra")
public class CompraAPI {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ICompraService service;
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Compra>> getAll() {
		List<Compra> compras = null;
		try {
			compras = service.findAll();
		} catch (Exception e) {
			return new ResponseEntity<List<Compra>>(compras, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Compra>>(compras, HttpStatus.OK);
	}
	
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> create(@RequestBody Compra compra) {
		int estado = 0;
		logger.info("Compra id:" + compra.getIdCompra());
		logger.info("Farmacia id:" + compra.getFarmacia().getIdFarmacia());
		
		try {
			estado = service.create(compra);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(estado, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(estado, HttpStatus.OK);
	}
}
