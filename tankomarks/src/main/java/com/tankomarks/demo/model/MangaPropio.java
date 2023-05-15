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
@Table(name = "manga_propio")
public class MangaPropio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_manga_propio")
	private int id_manga_propio;
	
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
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_id_usuario")
	private Usuario usuario_id_usuario;

	public MangaPropio() {
		super();
	}

	public MangaPropio(int id_manga_propio, @NotNull String nombre, @NotNull String descripcion,
			@NotNull String enlacefoto, @NotNull Demografia demografia, @NotNull Usuario usuario_id_usuario) {
		super();
		this.id_manga_propio = id_manga_propio;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.enlacefoto = enlacefoto;
		this.demografia = demografia;
		this.usuario_id_usuario = usuario_id_usuario;
	}

	public int getId_manga_propio() {
		return id_manga_propio;
	}

	public void setId_manga_propio(int id_manga_propio) {
		this.id_manga_propio = id_manga_propio;
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
