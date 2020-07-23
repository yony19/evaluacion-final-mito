package com.mitocode.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Farmacia;
import com.mitocode.model.Medicamento;

public interface IFarmaciaDAO extends JpaRepository<Farmacia, Integer> {

	public List<Farmacia> findByMedicamento(Medicamento medicamento);
	
}
