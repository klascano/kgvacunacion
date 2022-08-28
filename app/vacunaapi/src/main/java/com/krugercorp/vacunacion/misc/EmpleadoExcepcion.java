package com.krugercorp.vacunacion.misc;

public class EmpleadoExcepcion extends RuntimeException {

	private static final long serialVersionUID = 6431603845942149505L;

	public EmpleadoExcepcion(Long id) {
		super("empleado no encontrado " + id);
	}
	
}
