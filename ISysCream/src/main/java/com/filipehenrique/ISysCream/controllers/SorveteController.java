package com.filipehenrique.ISysCream.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filipehenrique.ISysCream.entities.Sorvete;
import com.filipehenrique.ISysCream.services.SorveteService;

@RestController
@RequestMapping("/sorvete")
public class SorveteController {
	
	@Autowired
	private SorveteService sorveteService;
	
	@PostMapping
	public ResponseEntity<Sorvete> insert (@RequestBody Sorvete sorvete){		
		try {
			sorveteService.insert(sorvete);
			return ResponseEntity.status(HttpStatus.CREATED).body(sorvete);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}	
	}
	

}
