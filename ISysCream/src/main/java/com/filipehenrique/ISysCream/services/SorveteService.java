package com.filipehenrique.ISysCream.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filipehenrique.ISysCream.entities.Sorvete;
import com.filipehenrique.ISysCream.repositories.SorveteRepository;

@Service
public class SorveteService {
	
	@Autowired
	private SorveteRepository sorveteRepository;
	
	public void insert(Sorvete t) throws SQLException {
		this.sorveteRepository.insert(t);;
	}
	
	

}
