package com.krugercorp.vacunacion.persistencia.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.krugercorp.vacunacion.persistencia.entidad.Vacuna;

public interface VacunaRepositorio extends CrudRepository<Vacuna, Long> {

}
