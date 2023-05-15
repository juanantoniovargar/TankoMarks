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
@Table(name = "manga")
public class Manga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_manga")
	private int id_manga;
	
	@NotNull
	@Column(name = "nombre")
	private String nombre;
	
	@NotNull
	@Column(name = "descripcion")
	private String descripcion;
	
	@NotNull
	@Column(name = "enlacefoto")
	private String enlacefoto;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "demografia_id_demografia")
	private Demografia demografia;

	public Manga() {
		super();
	}

	public Manga(int id_manga, String nombre, String descripcion, String enlacefoto, Demografia demografia) {
		super();
		this.id_manga = id_manga;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.enlacefoto = enlacefoto;
		this.demografia = demografia;
	}

	public int getId_manga() {
		return id_manga;
	}

	public void setId_manga(int id_manga) {
		this.id_manga = id_manga;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEnlacefoto() {
		return enlacefoto;
	}

	public void setEnlacefoto(String enlacefoto) {
		this.enlacefoto = enlacefoto;
	}

	public Demografia getDemografia() {
		return demografia;
	}

	public void setDemografia(Demografia demografia) {
		this.demografia = demografia;
	}

}
