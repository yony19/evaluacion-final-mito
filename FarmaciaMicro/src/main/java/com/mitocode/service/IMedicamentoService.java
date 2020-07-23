package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Medicamento;

public interface IMedicamentoService extends CRUD<Medicamento> {
	
	public List<Medicamento> findByNombreMedicamento(String nombreMedicamento);
	
}
