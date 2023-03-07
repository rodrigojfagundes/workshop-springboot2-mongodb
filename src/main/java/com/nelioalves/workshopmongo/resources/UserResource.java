package com.nelioalves.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.services.UserService;


@RestController
	//colocando o @RequestMapping para dizer qual e o caminho
	//do ENDPOINT... Ou seja o caminho q sera solicitado pelo
	//navegador... ex localhost:8080/users
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity< List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		
		//retornando um ResponseEntity OK, para dizer q a resposta
		//ocorreu com sucesso...
		//e no BODY no vamos passar o CORPO da RESPOSTA no caso 
		//a LISTDTO de usuarios feita ali em cima
		return ResponseEntity.ok().body(listDto);
	}
	
	//criando um metodo para retornar o usuario por ID
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
public ResponseEntity<UserDTO> findById(@PathVariable String id){
	User obj = service.findById(id);
	
	return ResponseEntity.ok().body(new UserDTO(obj));
}

	@RequestMapping(method=RequestMethod.POST)
public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){

	User obj = service.fromDTO(objDto);

	obj = service.insert(obj);
	
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id){
	service.delete(id);
	return ResponseEntity.noContent().build();
	}
	
	
	//criando um metodo para ATUALIZAR o usuario
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id){
	
	//instanciando um OBJ do tipo USUARIO/USER recebendo o valor
	//de SERVICE.FROMDTO... Pois assim nos CONVERTEMOS um USERDTO
	//para USUARIO, e esse USERDTO convertido para USUARIO
		//vai ficar armazenado no OBJETO OBJ
	User obj = service.fromDTO(objDto);
	obj.setId(id);
	obj = service.update(obj);
	
	return ResponseEntity.noContent().build();
	}
}