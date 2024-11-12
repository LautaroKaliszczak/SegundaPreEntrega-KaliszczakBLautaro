package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Comercio;
import com.coderhouse.models.Producto;
import com.coderhouse.repositories.ComercioRepository;
import com.coderhouse.repositories.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ComercioService {

	@Autowired
	private ComercioRepository comercioRepository;

	@Autowired
	private ProductoRepository productoRepository;

	public List<Comercio> getAllComercios() {
		return comercioRepository.findAll();
	}

	public Comercio findById(Long id) {
		return comercioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Comercio no encontrado"));
	}

	@Transactional
	public Comercio saveComercio(Comercio comercio) {
		return comercioRepository.save(comercio);
	}

	@Transactional
	public Comercio updateComercio(Long id, Comercio comercioDetails) {
		Comercio comercio = comercioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Comercio no encontrado"));
		comercio.setNombre(comercioDetails.getNombre());
		return comercioRepository.save(comercio);

	}

	public void deleteComercio(Long id) {
		if (!comercioRepository.existsById(id)) {
			throw new IllegalArgumentException("Comercio no encontrado");
		}
		comercioRepository.deleteById(id);

	}
	
	/*@Transactional
	public Comercio asignarProductoAComercio(Long comercioId, Long productoId) {
	    Producto producto = productoRepository.findById(productoId)
	            .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

	    Comercio comercio = comercioRepository.findById(comercioId)
	            .orElseThrow(() -> new IllegalArgumentException("Comercio no encontrado"));
	    if (!comercio.getProductos().contains(producto)) {
	        comercio.getProductos().add(producto);
	    }
	   
	    comercio.getProductos().add(producto);


	    return comercioRepository.save(comercio);
	}*/

}
