package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IFarmaciaDAO;
import com.mitocode.model.Farmacia;
import com.mitocode.model.Medicamento;
import com.mitocode.service.IFarmaciaService;
import com.mitocode.service.IMedicamentoService;

@Service
public class FarmaciaServiceImpl implements IFarmaciaService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IFarmaciaDAO dao;

	@Autowired
	private IMedicamentoService medicamentoService;
	
	public List<Farmacia> findByMedicamentoId(Integer medicamentoId) 
	{
		List<Farmacia> farmacias = null;
		logger.info("Entra a metodo buscar por id");
		Medicamento medicamento = medicamentoService.find(medicamentoId);
		logger.info("Encontro medicamento:" + medicamento.getNombreMedicamento());
		if (medicamento !=null ) {
			farmacias = dao.findByMedicamento(medicamento);
		}
		
		return farmacias;
	}
	
	@Override
	public List<Farmacia> findAll() {
		return dao.findAll();
	}

	@Override
	public Integer create(Farmacia entity) {
		Farmacia farmacia = dao.save(entity);
		return farmacia !=null ? 1 : 0;
	}

	@Override
	public Farmacia find(Integer id) {
		return dao.getOne(id);
	}

	@Override
	public Farmacia update(Farmacia entity) {
		return dao.save(entity);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

}
