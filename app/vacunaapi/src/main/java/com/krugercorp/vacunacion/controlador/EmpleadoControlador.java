package com.krugercorp.vacunacion.controlador;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.krugercorp.vacunacion.misc.Util;
import com.krugercorp.vacunacion.persistencia.entidad.Empleado;
import com.krugercorp.vacunacion.persistencia.entidad.Usuario;
import com.krugercorp.vacunacion.persistencia.entidad.Vacuna;
import com.krugercorp.vacunacion.persistencia.repositorio.EmpleadoRepositorio;
import com.krugercorp.vacunacion.persistencia.repositorio.UsuarioRepositorio;
import com.krugercorp.vacunacion.persistencia.repositorio.VacunaRepositorio;

@RestController
@CrossOrigin
public class EmpleadoControlador {

	private final EmpleadoRepositorio rep;
	private final VacunaRepositorio repvac;
	private final UsuarioRepositorio repusu;
	
	 @PersistenceContext
	  EntityManager em;

	public EmpleadoControlador(EmpleadoRepositorio rep, VacunaRepositorio repvac, UsuarioRepositorio repusu) {
		this.rep = rep;
		this.repvac = repvac;
		this.repusu = repusu;
	}

	@PreAuthorize("hasAuthority('admin')")
	@GetMapping("/emps")
	List<Empleado> empleados() {
		return rep.findAll();
	}

	@PreAuthorize("hasAuthority('admin')")
	@GetMapping("/emps/ev/{estado}")
	List<Empleado> empleadoPorEstadoVacuna(@PathVariable Integer estado) {
		return rep.findByEstadovacunado(estado);
	}
	
	@PreAuthorize("hasAuthority('admin')")
	@GetMapping("/emps/tv/{tipo}")
	List<Empleado> empleadoPorEstadoVacuna(@PathVariable String tipo) {
		 Query q = em.createNamedQuery("findByTipoVacuna");      
	        q.setParameter(1, tipo);
	        return q.getResultList();
	}
	
	@PreAuthorize("hasAuthority('admin')")
	@GetMapping("/emps/fv/{i}/{f}")
	List<Empleado> empleadoPorEstadoVacuna(@PathVariable @DateTimeFormat(pattern = "ddMMyyyy") Date i,@PathVariable @DateTimeFormat(pattern = "ddMMyyyy") Date f) {
		 Query q = em.createNamedQuery("findByFechaVacuna");      
	        q.setParameter(1, i);
	        q.setParameter(2, f);
	        return q.getResultList();
	}

	@PreAuthorize("hasAuthority('admin')")
	@PostMapping("/emps")
	ResponseEntity<?> nuevoEmpleado(@RequestBody Empleado ne) {

		ResponseEntity<?> resp = null;
		Empleado emp = null;
		Usuario usu = null;

		if (ne.getCi() != null && new Util().validadorDeCedula(ne.getCi()) && new Util().validarCorreo(ne.getCorreo())
				&& new Util().validarLetras(ne.getApellidos()) && new Util().validarLetras(ne.getNombres())) {

			emp = rep.findByCi(ne.getCi());

			if (emp==null || emp.getId() == null) {
				emp = ne;
			} else {
				emp.setNombres(ne.getNombres());
				emp.setApellidos(ne.getApellidos());
				emp.setCorreo(ne.getCorreo());
				emp.setCi(ne.getCi());
			}
			
			emp = rep.save(emp);

			if (emp.getUsuarios()==null||emp.getUsuarios().size() == 0) {

				usu = new Usuario();
				usu.setUsuario(emp.getCi());
				usu.setContrasena(new BCryptPasswordEncoder().encode(emp.getCi()));
				//usu.setContrasena(new BCryptPasswordEncoder().encode("0103897955"));
				usu.setRol("empleado");
				usu.setEmpleado(emp);

				repusu.save(usu);

			}

			resp = ResponseEntity.ok(emp);
		} else {
			resp = ResponseEntity.badRequest().body(
					"verifique sus datos: cedula 10 caracteres y numerica, correo valido, nombres y apellidos solo letras");
		}

		return resp;
	}

	@PreAuthorize("hasAuthority('empleado')")
	@PutMapping("/emp/{id}")
	ResponseEntity<?> editarEmpleado(@RequestBody Empleado ne, @PathVariable Long id) {

		ResponseEntity<?> resp = null;
		Empleado emp = null;

		try {
			Optional<Empleado> oemp = rep.findById(id);
			if (oemp.isPresent() && ne.getEstadovacunado().intValue() == 1 && ne.getVacunas()!=null && ne.getVacunas().size() == 1) {

				emp = oemp.get();

				emp.setFechanacimiento(ne.getFechanacimiento());
				emp.setDireccion(ne.getDireccion());
				emp.setTelfonomovil(ne.getTelfonomovil());
				emp.setEstadovacunado(ne.getEstadovacunado());
				rep.save(emp);

				Vacuna vac = ne.getVacunas().get(0);
				vac.setEmpleado(emp);
				repvac.save(vac);

				resp = ResponseEntity.ok(emp);

			} else {
				resp = ResponseEntity.badRequest().body(
						"verifique sus datos: fecha nacimiento, direccion, telefono y si esta vacunado: la informacion de su dosis.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			resp = ResponseEntity.internalServerError().body("Error interno");
		}

		return resp;
	}

}
