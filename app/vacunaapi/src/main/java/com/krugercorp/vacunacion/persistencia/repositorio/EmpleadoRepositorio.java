package com.krugercorp.vacunacion.persistencia.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.krugercorp.vacunacion.persistencia.entidad.Empleado;

public interface EmpleadoRepositorio extends CrudRepository<Empleado, Long> {
	
	List<Empleado> findByEstadovacunado ( Integer estadovacunado );	
	
	Empleado findByCi( String ci );
	
	List<Empleado> findAll();		
	
}
