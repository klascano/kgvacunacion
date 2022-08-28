package com.krugercorp.vacunacion.persistencia.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.krugercorp.vacunacion.persistencia.entidad.Usuario;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {
		
    Usuario findByUsuario(String usuario);

}
