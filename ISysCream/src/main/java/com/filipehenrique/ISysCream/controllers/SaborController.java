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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.filipehenrique.ISysCream.entities.Sabor;
import com.filipehenrique.ISysCream.services.SaborService;

@RestController
@RequestMapping("/sabor")
public class SaborController {
	
	@Autowired
	private SaborService saborService;
	

	@PostMapping
	public ResponseEntity<Sabor> insert (@RequestBody Sabor sabor){		
		try {
			saborService.insert(sabor);
			return ResponseEntity.status(HttpStatus.CREATED).body(sabor);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}	
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Sabor> findById(@PathVariable Integer id) {
		try {
			Sabor sabor = saborService.findById(id);
			return ResponseEntity.ok().body(sabor);
			
		} catch (SQLException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	@GetMapping
	public ResponseEntity<List<Sabor>> findAll(){
		
		try {
			List<Sabor> sabores=  saborService.findAll();
			return ResponseEntity.ok(sabores);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}				
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id ){
		try {
			saborService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping
    public ResponseEntity<Void> update(@RequestBody Sabor sabor) {
        try {
            saborService.update(sabor);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
