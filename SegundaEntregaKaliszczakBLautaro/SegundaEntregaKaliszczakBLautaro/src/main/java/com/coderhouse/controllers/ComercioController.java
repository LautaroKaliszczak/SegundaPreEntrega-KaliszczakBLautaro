package com.coderhouse.controllers;

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

import com.coderhouse.models.Comercio;
import com.coderhouse.services.ComercioService;

@RestController
@RequestMapping("/api/comercios")
public class ComercioController {

	@Autowired
	private ComercioService comercioService;

	@GetMapping
	public ResponseEntity<List<Comercio>> getAllComercios() {
		try {
			List<Comercio> comercio = comercioService.getAllComercios();
			return ResponseEntity.ok(comercio);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Comercio> getComercioById(@PathVariable long id) {
		try {
			Comercio comercio = comercioService.findById(id);
			return ResponseEntity.ok(comercio);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping
	public ResponseEntity<Comercio> createComercio(@RequestBody Comercio comercio) {
		try {
			Comercio createdComercio = comercioService.saveComercio(comercio);
			return ResponseEntity.ok(createdComercio);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Comercio> updateComercioById(@PathVariable Long id, @RequestBody Comercio comercioDetails) {
		try {
			Comercio updatedComercio = comercioService.updateComercio(id, comercioDetails);
			return ResponseEntity.ok(updatedComercio);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteComercio(@PathVariable Long id) {
		try {
			comercioService.deleteComercio(id);
			return ResponseEntity.noContent().build();

		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	/*@PostMapping("/asignar-producto")
	public ResponseEntity<Comercio> asignarProductoAComercio(@RequestBody AsignarProductoDTO asignarProductoDTO){
		try {
			
			Comercio comercioActualizado = comercioService.asignarProductoAComercio(
					asignarProductoDTO.getComercioId(),
					asignarProductoDTO.getProductoId()
					);
			return ResponseEntity.ok(comercioActualizado);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}*/
}



