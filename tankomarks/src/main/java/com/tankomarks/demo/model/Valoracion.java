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
@Table(name = "valoracion")
public class Valoracion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_valoracion")
	private int id_valoracion;
	
	@Column(name = "comentario")
	private String comentario;
	
	@Column(name = "favorito")
	private boolean favorito;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "capitulo_id_capitulo")
	private Capitulo capitulo;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_id_usuario")
	private Usuario usuario_id_usuario;

	public Valoracion() {
		super();
	}

	public Valoracion(int id_valoracion, String comentario, boolean favorito, @NotNull Capitulo capitulo,
			@NotNull Usuario usuario_id_usuario) {
		super();
		this.id_valoracion = id_valoracion;
		this.comentario = comentario;
		this.favorito = favorito;
		this.capitulo = capitulo;
		this.usuario_id_usuario = usuario_id_usuario;
	}

	public int getId_valoracion() {
		return id_valoracion;
	}

	public void setId_valoracion(int id_valoracion) {
		this.id_valoracion = id_valoracion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public boolean isFavorito() {
		return favorito;
	}

	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}

	public Capitulo getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(Capitulo capitulo) {
		this.capitulo = capitulo;
	}

	public Usuario getUsuario_id_usuario() {
		return usuario_id_usuario;
	}

	public void setUsuario_id_usuario(Usuario usuario_id_usuario) {
		this.usuario_id_usuario = usuario_id_usuario;
	}

}
