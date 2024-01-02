package com.filipehenrique.ISysCream.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping ResponseEntity<List<Sorvete>> findAll (){
		List<Sorvete> sorvetes;
		try {
			sorvetes = sorveteService.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
		}
		
		return ResponseEntity.ok().body(sorvetes);
	}
	
	@GetMapping("/{id}") 
	ResponseEntity<Sorvete> findById(@PathVariable Integer id) {
			try {
				Sorvete sorvete= sorveteService.findById(id);
				return ResponseEntity.ok().body(sorvete);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ResponseEntity.badRequest().build();
			}
				
	}
	
	
	@DeleteMapping("/{id}") 
	ResponseEntity<Void> delete (@PathVariable Integer id) {
		try {
			sorveteService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	

}
