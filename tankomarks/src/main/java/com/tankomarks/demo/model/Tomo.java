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
@Table(name = "tomo")
public class Tomo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tomo")
	private int id_tomo;
	
	@NotNull
	@Column(name = "numero")
	private int numero;
	
	@NotNull
	@Column(name = "enlacefoto")
	private String enlacefoto;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "manga_id_manga")
	private Manga manga;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "manga_propio_id_manga_propio")
	private MangaPropio mangaPropio;

	public Tomo() {
		super();
	}

	public Tomo(int id_tomo, @NotNull int numero, @NotNull String enlacefoto, Manga manga, MangaPropio mangaPropio) {
		super();
		this.id_tomo = id_tomo;
		this.numero = numero;
		this.enlacefoto = enlacefoto;
		this.manga = manga;
		this.mangaPropio = mangaPropio;
	}

	public int getId_tomo() {
		return id_tomo;
	}

	public void setId_tomo(int id_tomo) {
		this.id_tomo = id_tomo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getEnlacefoto() {
		return enlacefoto;
	}

	public void setEnlacefoto(String enlacefoto) {
		this.enlacefoto = enlacefoto;
	}

	public Manga getManga() {
		return manga;
	}

	public void setManga(Manga manga) {
		this.manga = manga;
	}

	public MangaPropio getMangaPropio() {
		return mangaPropio;
	}

	public void setMangaPropio(MangaPropio mangaPropio) {
		this.mangaPropio = mangaPropio;
	}

}
