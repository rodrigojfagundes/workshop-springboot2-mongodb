//		INFORMACAO IMPORTANTE SOBRE TUDO NO PROJETO

//fazendo a CONEXAO com o MONGODB com o REPOSITORY e o SERVICES...
//(pois estamos trabalhando com CAMADAS)... 
//FRONT-END > solicita ao [back-end] >  Controladores REST(RESOURCERS) 
//ex: UserResource
//e os CONTROLADORES REST(resource) vao SOLICITAR 
//OS SERVICES/(Camada de SERVICOS) e esses SERVICOS (q sao metodos q 
//estao na CAMADA DE SERVICO)
//EX: metodos q estao dentro do UserService
//vao SOLICITAR os OBJ q estao na camada de ACESSO a DADOS os REPOSITORY...
//Ex: UserRepository

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

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.services.UserService;

//classe USERRESOURCE dentro do SUBPACOTE RESOURCES,
//o nome é RESOURCE pois o nome tecnico para referenciar
//RECURSOS REST é RESOURCE... Ou seja são RECURSOS q o BACKEND
//vai DISPONIBILIZAR... tbm pd ser chamado de 
//CONTROLLER/CONTROLADOR

@RestController
	//colocando o @RequestMapping para dizer qual e o caminho
	//do ENDPOINT... Ou seja o caminho q sera solicitado pelo
	//navegador... ex localhost:8080/users
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	//
	//
	//crindo um metodo FINDALL q CHAMA o USERSERVICE e pede para
	//ele os USUARIOS cadastrados no BANCO
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity< List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
	//criando um metodo para retornar o usuario por ID	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
public ResponseEntity<UserDTO> findById(@PathVariable String id){
	
	//instanciando um OBJ do tipo USUARIO/USER recebendo o valor
	//do SERVICE.findById
	User obj = service.findById(id);
	return ResponseEntity.ok().body(new UserDTO(obj));
}
	
	//criando o metodo /ENDPOIT para inserir usuario...
	@RequestMapping(method=RequestMethod.POST)
public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
	
	User obj = service.fromDTO(objDto);
	obj = service.insert(obj);
	
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	return ResponseEntity.created(uri).build();
	}
	
	//criando um metodo para DELETAR o usuario por ID
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)

	public ResponseEntity<Void> delete(@PathVariable String id){
	//
	//chamando o service e chamando o metodo DELETE e passando
	//o ID do usuario q queremos DELETAR
	service.delete(id);
	
	//resposta com o codigo 204, e uma resposta q DEU CERTO mas
	//q nao tem resposta, pois foi deletado o usuario
	return ResponseEntity.noContent().build();
	}
	
	//criando um metodo para ATUALIZAR o usuario	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id){
	User obj = service.fromDTO(objDto);
	obj.setId(id);
	obj = service.update(obj);
	
	return ResponseEntity.noContent().build();
	}
	
	

	//A LOGICA E SEMELHANTE A DO FINDBYID
	//
	//criando um ENDPOINT/METODO para pegarmos os POSTS de um USUARIO
	//pelo navegador/postman
	//sera... caminho USERS/ID_DO_USUARIO/POSTS	
	@RequestMapping(value="/{id}/posts", method=RequestMethod.GET)
public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
	User obj = service.findById(id);
	return ResponseEntity.ok().body(obj.getPosts());
}	
}