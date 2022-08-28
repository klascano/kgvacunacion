package com.krugercorp.vacunacion.misc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.krugercorp.vacunacion.persistencia.dto.UsuarioDto;
import com.krugercorp.vacunacion.persistencia.entidad.Usuario;
import com.krugercorp.vacunacion.persistencia.repositorio.UsuarioRepositorio;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio userDao;	

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = userDao.findByUsuario(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsuario(), user.getContrasena(),
				new ArrayList<>());
	}
	
	public Usuario save(UsuarioDto user) {
		Usuario newUser = new Usuario();
		newUser.setUsuario(user.getUsuario());
		newUser.setContrasena(bcryptEncoder.encode(user.getContrasena()));
		return userDao.save(newUser);
	}
	

}
