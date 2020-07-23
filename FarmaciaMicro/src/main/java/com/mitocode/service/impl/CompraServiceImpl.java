package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dao.ICompraDAO;
import com.mitocode.model.Compra;
import com.mitocode.service.ICompraService;

@Service
public class CompraServiceImpl implements ICompraService {

	@Autowired
	private ICompraDAO dao;
	
	@Override
	public List<Compra> findAll() {
		return dao.findAll();
	}

	@Override
	public Integer create(Compra entity) {
		Compra compra = dao.save(entity);
		return compra != null ? 1 : 0;
	}

	@Override
	public Compra find(Integer id) {
		return dao.getOne(id);
	}

	@Override
	public Compra update(Compra entity) {
		return dao.save(entity);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

}
