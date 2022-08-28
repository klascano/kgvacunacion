package com.krugercorp.vacunacion.persistencia.entidad;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIO_ID_GENERATOR", sequenceName="SQ_USUARIO_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_ID_GENERATOR")
	private Long id;

	private String actualizadopor;

	private String contrasena;

	private Integer estado = 1;

	private Timestamp fechaactualiza;

	private Timestamp fechainserta;

	private String insertadopor;

	private String rol;

	private String usuario;

	//bi-directional many-to-one association to Empleadonew Empleado(1L,"Lascano Sumbana", "0103897955","Klever Vinicio"))
	@JsonBackReference
	@ManyToOne
	private Empleado empleado;

	public Usuario() {
	}
	
	public Usuario(String usuario, Empleado empleado) {
		super();		
		this.usuario = usuario;
		this.empleado = empleado;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActualizadopor() {
		return this.actualizadopor;
	}

	public void setActualizadopor(String actualizadopor) {
		this.actualizadopor = actualizadopor;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Timestamp getFechaactualiza() {
		return this.fechaactualiza;
	}

	public void setFechaactualiza(Timestamp fechaactualiza) {
		this.fechaactualiza = fechaactualiza;
	}

	public Timestamp getFechainserta() {
		return this.fechainserta;
	}

	public void setFechainserta(Timestamp fechainserta) {
		this.fechainserta = fechainserta;
	}

	public String getInsertadopor() {
		return this.insertadopor;
	}

	public void setInsertadopor(String insertadopor) {
		this.insertadopor = insertadopor;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

}