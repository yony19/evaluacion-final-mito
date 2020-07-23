package com.mitocode.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.Medicamento;
import com.mitocode.service.IMedicamentoService;

@RestController
@RequestMapping("/medicamento")
public class MedicamentoAPI {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IMedicamentoService service;
	
	@GetMapping(value = "/buscar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Medicamento>> buscarMedicamentosByNombre(
			@RequestParam(value = "nombre", defaultValue = "", required = false) String nombre) {
		List<Medicamento> medicamentos = null;
		try {
			medicamentos = service.findByNombreMedicamento(nombre);
			logger.info("Encontrado: " + medicamentos.size());
		} catch (Exception e) {
			return new ResponseEntity<List<Medicamento>>(medicamentos, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Medicamento>>(medicamentos, HttpStatus.OK);
	}
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Medicamento>> obtenerTodos(
			@RequestParam(value = "nombre", defaultValue = "", required = false) String nombre) {
		List<Medicamento> medicamentos = null;
		try {
			medicamentos = service.findAll();
			logger.info("Encontrado: " + medicamentos.size());
		} catch (Exception e) {
			return new ResponseEntity<List<Medicamento>>(medicamentos, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Medicamento>>(medicamentos, HttpStatus.OK);
	}
}
