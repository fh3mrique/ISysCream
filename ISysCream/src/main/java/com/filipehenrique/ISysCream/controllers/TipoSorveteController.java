package com.filipehenrique.ISysCream.controllers;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filipehenrique.ISysCream.entities.TipoSorvete;
import com.filipehenrique.ISysCream.services.TipoSorveteService;

@RestController
@RequestMapping("/tiposorvete")
public class TipoSorveteController {
	
	@PostMapping
	public ResponseEntity<TipoSorvete> insert (@RequestBody TipoSorvete tipoSorvete){
		
		try {
			TipoSorveteService.getCurrentInstance().insert(tipoSorvete);
			return ResponseEntity.status(HttpStatus.CREATED).body(tipoSorvete);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}	
	}

}
