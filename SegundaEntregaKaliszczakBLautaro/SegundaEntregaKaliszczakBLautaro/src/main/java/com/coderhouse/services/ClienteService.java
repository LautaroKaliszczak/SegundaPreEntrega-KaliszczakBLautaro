package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Cliente;
import com.coderhouse.models.Comercio;
import com.coderhouse.models.Producto;
import com.coderhouse.repositories.ClienteRepository;
import com.coderhouse.repositories.ComercioRepository;
import com.coderhouse.repositories.ProductoRepository;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private ComercioRepository comercioRepository;
	
	public List<Cliente> getAllClientes() {
		return clienteRepository.findAll();
	}

	public Cliente findById(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
	}

	@Transactional
	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Transactional
	public Cliente updateCliente(Long id, Cliente clienteDetails) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

		cliente.setNombre(clienteDetails.getNombre());
		cliente.setApellido(clienteDetails.getApellido());

		if (clienteDetails.getTelefono() != null) {
			cliente.setTelefono(clienteDetails.getTelefono());
		}

		if (clienteDetails.getEmail() != null && !clienteDetails.getEmail().isEmpty()) {
			cliente.setEmail(clienteDetails.getEmail());
		}
		
		if (clienteDetails.getDireccion() != null && !clienteDetails.getDireccion().isEmpty()) {
			cliente.setDireccion(clienteDetails.getDireccion());
		}

		return clienteRepository.save(cliente);
	}

	public void deleteCliente(Long id) {
		if (!clienteRepository.existsById(id)) {
			throw new IllegalArgumentException("Cliente no encontrado");
		}
		clienteRepository.deleteById(id);

	}
	
	/*@Transactional
	public Cliente comprarProducto(CompraProductosDTO compraProductoDTO) {
	    Cliente cliente = clienteRepository.findById(compraProductoDTO.getClienteId())
	            .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
	    Producto producto = productoRepository.findById(compraProductoDTO.getProductoId())
	            .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
	    Comercio comercio = comercioRepository.findById(compraProductoDTO.getComercioId())
	            .orElseThrow(() -> new IllegalArgumentException("Comercio no encontrado"));

	    if (!comercio.getProductos().contains(producto)) {
	        throw new IllegalArgumentException("El producto no pertenece al comercio seleccionado.");
	    }
	    if (producto.getStock() < compraProductoDTO.getCantidad()) {
	        throw new IllegalArgumentException("Stock insuficiente para el producto " + producto.getNombre());
	    }
	    producto.setStock(producto.getStock() - compraProductoDTO.getCantidad());
	    productoRepository.save(producto);

	    String miniFactura = String.format(
	            cliente.getNombre(), cliente.getApellido(),
	            producto.getNombre(),
	            compraProductoDTO.getCantidad(),
	            producto.getPrecio(),
	            producto.getPrecio() * compraProductoDTO.getCantidad()
	        );
	    System.out.println(miniFactura);

	
	    return cliente;
	}*/

}