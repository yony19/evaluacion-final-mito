package com.mitocode.service;

import java.util.List;

public interface CRUD<T> {

	List<T> findAll();

	Integer create(T entity);

	T find(Integer id);

	T update(T entity);

	void delete(Integer id);
}
