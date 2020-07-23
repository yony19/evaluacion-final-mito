package com.mitocode.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.Farmacia;
import com.mitocode.service.IFarmaciaService;

@RestController
@RequestMapping("/farmacia")
public class FarmaciaAPI {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IFarmaciaService service;
	
	
	@GetMapping(value = "/{medicamentoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Farmacia>> getAllByMedicamento(
			@PathVariable("medicamentoId") String medicamentoId) 
	{
		logger.info("Entrando a metodo buscar por medicamento");
		
		List<Farmacia> farmacias = null;
		try {
			farmacias = service.findByMedicamentoId(Integer.parseInt(medicamentoId));
		} catch (Exception e) {
			return new ResponseEntity<List<Farmacia>>(farmacias, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("Fin size:" + farmacias.size());
		return new ResponseEntity<List<Farmacia>>(farmacias, HttpStatus.OK);
	}
}
