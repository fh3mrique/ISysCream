package com.filipehenrique.ISysCream.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filipehenrique.ISysCream.entities.Sorvete;
import com.filipehenrique.ISysCream.repositories.SorveteRepository;

@Service
public class SorveteService {
	
	@Autowired
	private SorveteRepository sorveteRepository;
	
	public void insert(Sorvete t) throws SQLException {
		this.sorveteRepository.insert(t);
	}
	
	public List<Sorvete> findAll () throws SQLException {
		return sorveteRepository.findAll();
	}
	
	public void delete (Integer id) throws SQLException {
		sorveteRepository.delete(id);
	}
	
	public Sorvete findById(Integer id) throws SQLException {
	   return this.sorveteRepository.findById(id);
	}
}
