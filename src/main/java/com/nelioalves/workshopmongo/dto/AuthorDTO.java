package com.nelioalves.workshopmongo.dto;

import java.io.Serializable;

import com.nelioalves.workshopmongo.domain.User;

//criando a class AUTHORDTO... e para 
//projetar os dados do AUTOR com DTO, pois com o DTO nos conseguimos
//FILTRAR os dados q vamos pegar, e no caso do AUTHOR de um
//POST ou COMENTARIO, queremos apenas pegar o NOME e o ID do user
//q no caso o USER é o AUTHOR q fez o post ou comentario...

public class AuthorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;

	public AuthorDTO() {
	}

	public AuthorDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}