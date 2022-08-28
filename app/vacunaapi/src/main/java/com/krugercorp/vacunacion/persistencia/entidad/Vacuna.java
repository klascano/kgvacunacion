package com.krugercorp.vacunacion.persistencia.entidad;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the vacuna database table.
 * 
 */
@Entity
@NamedQuery(name="Vacuna.findAll", query="SELECT v FROM Vacuna v")
public class Vacuna implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="VACUNA_ID_GENERATOR", sequenceName="SQ_VACUNA_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VACUNA_ID_GENERATOR")
	private Long id;

	private String actualizadopor;

	private Integer estado = 1;

	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	private Timestamp fechaactualiza;

	private Timestamp fechainserta;

	private String insertadopor;

	private BigDecimal numerodosis;

	private String tipo;

	//bi-directional many-to-one association to Empleado
	@JsonBackReference
	@ManyToOne
	private Empleado empleado;

	public Vacuna() {
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

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public BigDecimal getNumerodosis() {
		return this.numerodosis;
	}

	public void setNumerodosis(BigDecimal numerodosis) {
		this.numerodosis = numerodosis;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

}