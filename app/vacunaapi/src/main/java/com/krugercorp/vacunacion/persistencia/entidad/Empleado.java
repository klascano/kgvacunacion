package com.krugercorp.vacunacion.persistencia.entidad;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the empleado database table.
 * 
 */
@Entity
@NamedQuery(name="Empleado.findAll", query="SELECT e FROM Empleado e")
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "findByTipoVacuna",
        query =
            "SELECT * " +
            "FROM vacunacion.empleado e " +
            "WHERE EXISTS (" +
            "   SELECT v.empleado_id as id_e " +
            "   FROM vacunacion.vacuna v " +            
            "   WHERE v.tipo = ? and" +
            "	e.id = v.empleado_id"+
            ") ", resultClass = Empleado.class),
    @NamedNativeQuery(
            name = "findByFechaVacuna",
            query =
                "SELECT * " +
                "FROM vacunacion.empleado e " +
                "WHERE EXISTS (" +
                "   SELECT v.empleado_id as id_e " +
                "   FROM vacunacion.vacuna v " +            
                "   WHERE (v.fecha between ? and ?) and" +
                "	e.id = v.empleado_id"+
                ") ", resultClass = Empleado.class)
    
})
@Table(name = "empleado",schema="vacunacion")
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EMPLEADO_ID_GENERATOR", sequenceName="SQ_EMPLEADO_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMPLEADO_ID_GENERATOR")
	private Long id;

	private String actualizadopor;

	private String apellidos;

	private String ci;

	private String correo;

	private String direccion;

	private Integer estado = 1;

	private Integer estadovacunado;

	private Timestamp fechaactualiza;

	private Timestamp fechainserta;

	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	@Temporal(TemporalType.DATE)	
	private Date fechanacimiento;

	private String insertadopor;

	private String nombres;

	private Integer numerodosis;	

	private String telfonomovil;

	//bi-directional many-to-one association to Usuario
	@JsonManagedReference
	@OneToMany(mappedBy="empleado")
	private List<Usuario> usuarios;

	//bi-directional many-to-one association to Vacuna
	@JsonManagedReference
	@OneToMany(mappedBy="empleado")
	private List<Vacuna> vacunas;

	public Empleado() {
	}

	public Empleado( String apellidos, String ci, String nombres) {
		super();		
		this.apellidos = apellidos;
		this.ci = ci;
		this.nombres = nombres;
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

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCi() {
		return this.ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getEstadovacunado() {
		return this.estadovacunado;
	}

	public void setEstadovacunado(Integer estadovacunado) {
		this.estadovacunado = estadovacunado;
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

	public Date getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getInsertadopor() {
		return this.insertadopor;
	}

	public void setInsertadopor(String insertadopor) {
		this.insertadopor = insertadopor;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Integer getNumerodosis() {
		return this.numerodosis;
	}

	public void setNumerodosis(Integer numerodosis) {
		this.numerodosis = numerodosis;
	}

	public String getTelfonomovil() {
		return this.telfonomovil;
	}

	public void setTelfonomovil(String telfonomovil) {
		this.telfonomovil = telfonomovil;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setEmpleado(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setEmpleado(null);

		return usuario;
	}

	public List<Vacuna> getVacunas() {
		return this.vacunas;
	}

	public void setVacunas(List<Vacuna> vacunas) {
		this.vacunas = vacunas;
	}

	public Vacuna addVacuna(Vacuna vacuna) {
		getVacunas().add(vacuna);
		vacuna.setEmpleado(this);

		return vacuna;
	}

	public Vacuna removeVacuna(Vacuna vacuna) {
		getVacunas().remove(vacuna);
		vacuna.setEmpleado(null);

		return vacuna;
	}

}