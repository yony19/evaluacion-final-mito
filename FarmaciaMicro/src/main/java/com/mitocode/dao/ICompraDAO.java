package com.mitocode.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Compra;

public interface ICompraDAO extends JpaRepository<Compra, Integer>{
	
}
