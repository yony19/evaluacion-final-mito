package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Farmacia;

public interface IFarmaciaService extends CRUD<Farmacia>{
	
	public List<Farmacia> findByMedicamentoId(Integer medicamentoId);
	
}
