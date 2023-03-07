package com.nelioalves.workshopmongo.services.exception;

//classe OBJECTNOTFOUNDEXCEPTION q HERDA da RUNTIMEEXCEPTION, q e uma
//classe q e para manejar erros
public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
}
