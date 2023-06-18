package com.tankomarks.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "capitulo")
public class Capitulo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_capitulo")
	private int id_capitulo;
	
	@NotNull
	@Column(name = "nombre")
	private String nombre;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "tomo_id_tomo")
	private Tomo tomo;

	public Capitulo() {
		super();
	}

	public Capitulo(int id_capitulo, @NotNull String nombre, @NotNull Tomo tomo) {
		super();
		this.id_capitulo = id_capitulo;
		this.nombre = nombre;
		this.tomo = tomo;
	}

	public int getId_capitulo() {
		return id_capitulo;
	}

	public void setId_capitulo(int id_capitulo) {
		this.id_capitulo = id_capitulo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tomo getTomo() {
		return tomo;
	}

	public void setTomo(Tomo tomo) {
		this.tomo = tomo;
	}

}
