package com.tankomarks.demo.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private int id_usuario;
	
	@NotNull
	@Column(name = "nombre")
	private String nombre;
	
	@NotNull
	@Column(name = "email")
	private String email;
	
	@NotNull
	@Column(name = "password")
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "usuarios_roles", 
			   joinColumns = @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario"),
			   inverseJoinColumns = @JoinColumn(name = "rol_id_rol", referencedColumnName = "id_rol"))
	private Collection<Rol> rol;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "leyendo_manga", 
			   joinColumns = @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario"),
			   inverseJoinColumns = @JoinColumn(name = "manga_id_manga", referencedColumnName = "id_manga"))
	private Set<Manga> leyendo_manga;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "leido_manga", 
			   joinColumns = @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario"),
			   inverseJoinColumns = @JoinColumn(name = "manga_id_manga", referencedColumnName = "id_manga"))
	private Set<Manga> leido_manga;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "leyendo_tomo", 
			   joinColumns = @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario"),
			   inverseJoinColumns = @JoinColumn(name = "tomo_id_tomo", referencedColumnName = "id_tomo"))
	private Set<Tomo> leyendo_tomo;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "leido_tomo", 
			   joinColumns = @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario"),
			   inverseJoinColumns = @JoinColumn(name = "tomo_id_tomo", referencedColumnName = "id_tomo"))
	private Set<Tomo> leido_tomo;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "leido_capitulo", 
			   joinColumns = @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario"),
			   inverseJoinColumns = @JoinColumn(name = "capitulo_id_capitulo", referencedColumnName = "id_capitulo"))
	private Set<Capitulo> leido_capitulo;
	

	public Usuario() {
		super();
	}

	public Usuario(String nombre, String email, String password) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.password = password;
	}

	public Usuario(String nombre, String email, String password, Collection<Rol> rol) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.rol = rol;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Rol> getRol() {
		return rol;
	}

	public void setRol(Collection<Rol> rol) {
		this.rol = rol;
	}

	public Set<Manga> getLeyendo_manga() {
		return leyendo_manga;
	}

	public void setLeyendo_manga(Set<Manga> leyendo_manga) {
		this.leyendo_manga = leyendo_manga;
	}

	public Set<Manga> getLeido_manga() {
		return leido_manga;
	}

	public void setLeido_manga(Set<Manga> leido_manga) {
		this.leido_manga = leido_manga;
	}

	public Set<Tomo> getLeyendo_tomo() {
		return leyendo_tomo;
	}

	public void setLeyendo_tomo(Set<Tomo> leyendo_tomo) {
		this.leyendo_tomo = leyendo_tomo;
	}

	public Set<Tomo> getLeido_tomo() {
		return leido_tomo;
	}

	public void setLeido_tomo(Set<Tomo> leido_tomo) {
		this.leido_tomo = leido_tomo;
	}

	public Set<Capitulo> getLeido_capitulo() {
		return leido_capitulo;
	}

	public void setLeido_capitulo(Set<Capitulo> leido_capitulo) {
		this.leido_capitulo = leido_capitulo;
	}

}
