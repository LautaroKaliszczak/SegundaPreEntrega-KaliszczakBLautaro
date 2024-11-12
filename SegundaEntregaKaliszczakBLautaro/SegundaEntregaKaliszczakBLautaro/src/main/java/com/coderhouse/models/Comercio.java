package com.coderhouse.models;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Comercios")
public class Comercio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tipoComercio")
	private String tipoComercio;
	
	@Column(name = "Nombre", unique = true)
	private String nombre;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			 name = "comercio_cliente",
			 joinColumns = @JoinColumn(name = "comercio_id"),
			 inverseJoinColumns = @JoinColumn(name = "cliente_id")
			 )
	
	private List<Cliente> clientes = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	 @JoinTable(
			 name = "comercio_producto",
			 joinColumns = @JoinColumn(name = "comercio_id"),
			 inverseJoinColumns = @JoinColumn(name = "producto_id")
			 )
	
	
	private List<Producto> productos = new ArrayList<>();
	
	
	public Comercio() {
		super();
		
	}

	public Comercio(String tipoComercio, String nombre) {
		super();
		this.tipoComercio = tipoComercio;
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoComercio() {
		return tipoComercio;
	}

	public void setTipoComercio(String tipoComercio) {
		this.tipoComercio = tipoComercio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Producto> getProductos() {
		return productos;
	}
	
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	@Override
	public String toString() {
		return "Comercio [id=" + id + ", tipoComercio=" + tipoComercio + ", nombre=" + nombre + ", productos="
				+ productos + ",clientes="+clientes+"]";
	}
	
}

