package com.mitocode.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.User;

public interface UserDAO extends JpaRepository<User, Long> {
	
	User findOneByUsername(String username);
}