package com.mitocode.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Medicamento;

public interface IMedicamentoDAO extends JpaRepository<Medicamento, Integer>{
	
	public List<Medicamento> findByNombreMedicamentoContainingIgnoreCase(String nombreMedicamento);
	
	
	//@Query("from Medicamento m where m.nombre_medicamento like %%")
	//public List<Medicamento> findByNombre(@Param("nombre") String nombre);
	
}
