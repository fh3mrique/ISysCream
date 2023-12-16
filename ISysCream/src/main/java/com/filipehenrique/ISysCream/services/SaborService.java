package com.filipehenrique.ISysCream.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filipehenrique.ISysCream.entities.Sabor;
import com.filipehenrique.ISysCream.repositories.SaborRepository;

@Service
public class SaborService {
	
	@Autowired
	private SaborRepository saborRepository;
	
	public void insert(Sabor t) throws SQLException {
		this.saborRepository.insert(t);
	}
	
	public void update(Sabor t) throws SQLException {
		this.saborRepository.update(t);
	}
	
	public Sabor findById(int id) throws SQLException {
		return this.saborRepository.findById(id);
	}
	
	public void delete(int codigo) throws SQLException {
		this.saborRepository.delete(codigo);
	}
	
	public List<Sabor> findAll() throws SQLException{
		return this.saborRepository.findAll();
	}

}
