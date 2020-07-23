package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IMedicamentoDAO;
import com.mitocode.model.Medicamento;
import com.mitocode.service.IMedicamentoService;

@Service
public class MedicamentoServiceImpl implements IMedicamentoService {

	@Autowired
	private IMedicamentoDAO dao;
	
	@Override
	public List<Medicamento> findAll() {
		return dao.findAll();
	}
	
	public List<Medicamento> findByNombreMedicamento(String nombreMedicamento) 
	{
		return dao.findByNombreMedicamentoContainingIgnoreCase(nombreMedicamento);
	}
	
	@Override
	public Integer create(Medicamento entity) {
		Medicamento medicamento = dao.save(entity);
		return medicamento.getIdMedicamento() > 0 ? 1 : 0;
	}

	@Override
	public Medicamento find(Integer id) {
		return dao.getOne(id);
	}

	@Override
	public Medicamento update(Medicamento entity) {
		return dao.save(entity);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

}
