package com.krugercorp.vacunacion.misc;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class EmpleadoAviso {
	
	 @ResponseBody
	  @ExceptionHandler(EmpleadoExcepcion.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  String EmpleadoAvisoManejador(EmpleadoExcepcion ex) {
	    return ex.getMessage();
	  }

}
