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
	
	@Column(name = "enlacefoto")
	private String enlacefoto;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "demografia_id_demografia")
	private Demografia demografia;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_id_usuario")
	private Usuario usuario_id_usuario;

	public Manga() {
		super();
	}

	public Manga(int id_manga, @NotNull String nombre, @NotNull String descripcion, String enlacefoto,
			@NotNull Demografia demografia, Usuario usuario_id_usuario) {
		super();
		this.id_manga = id_manga;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.enlacefoto = enlacefoto;
		this.demografia = demografia;
		this.usuario_id_usuario = usuario_id_usuario;
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

	public Usuario getUsuario_id_usuario() {
		return usuario_id_usuario;
	}

	public void setUsuario_id_usuario(Usuario usuario_id_usuario) {
		this.usuario_id_usuario = usuario_id_usuario;
	}

}
