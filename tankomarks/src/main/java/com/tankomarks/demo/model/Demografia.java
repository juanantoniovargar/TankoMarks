package com.tankomarks.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "demografia")
public class Demografia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_demografia")
	private int id_demografia;
	
	@NotNull
	@Column(name = "nombre")
	private String nombre;

	public Demografia() {
		super();
	}

	public Demografia(int id_demografia, String nombre) {
		super();
		this.id_demografia = id_demografia;
		this.nombre = nombre;
	}

	public int getId_demografia() {
		return id_demografia;
	}

	public void setId_demografia(int id_demografia) {
		this.id_demografia = id_demografia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
