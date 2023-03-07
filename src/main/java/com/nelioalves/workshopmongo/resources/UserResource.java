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
	//
	//para dizer q esse METODO vai ser o ENDPOINT REST do 
	//CAMINHO /USERS vamos colocar o @RequestMapping e 
	//passar o METHOD informando q o METODO e o GET pois o 
	//GET e para PEGAR DADOS no padrao REST
	//
	@RequestMapping(method=RequestMethod.GET)
		//o ResponseEntity e para retornar RESPOSTAS em HTTP ja com
	public ResponseEntity< List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		//pegando o q ta na LIST do tipo USER e passando para a LIST do tipo
		//USERDTO...
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	//criando um metodo para retornar o usuario por ID
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
public ResponseEntity<UserDTO> findById(@PathVariable String id){
	
	//instanciando um OBJ do tipo USUARIO/USER recebendo o valor
	//do SERVICE.findById, e passando como parametro
	//o STRING ID q recebemos ali no metodo acima String id...
	User obj = service.findById(id);
	
	//retornando um ResponseEntity OK, para dizer q a resposta
	//ocorreu com sucesso...
	//e no BODY no vamos passar o nosso OBJ do tipo USER q sera
	//CONVERTIDO para USERDTO
	return ResponseEntity.ok().body(new UserDTO(obj));
}
	
	//criando o metodo /ENDPOIT para inserir usuario...
	//ele recebe a solicitacao do FRONT e passa para a camada
	//de service...
	@RequestMapping(method=RequestMethod.POST)
	//o ResponseEntity e para retornar RESPOSTAS em HTTP ja com
	//cabecalhos, etc... Mas como vamos INSERIR nao precisa
	//retornar NADA, pois nao ESTAMOS PEGANDO e sim INSERINDO
	//por isso é VOID
	//
	//o ENDPOINT INSERT vai receber como ARGUMENTO o USERDTO
public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
	
	User obj = service.fromDTO(objDto);
	obj = service.insert(obj);
	
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	return ResponseEntity.created(uri).build();
	}
	
	//criando um metodo para DELETAR o usuario por ID
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	//o ResponseEntity e para retornar RESPOSTAS em HTTP ja com
	//cabecalhos, etc... a RESPOSTA do metodo vai ser VOID/vazia
	//pois estamos DELETANDO
	public ResponseEntity<Void> delete(@PathVariable String id){
	service.delete(id);
	
	return ResponseEntity.noContent().build();
	}
	
		
	//metodo para ATUALIZAR o metodo é .PUT...
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	//o ENDPOINT INSERT vai receber como ARGUMENTO o OBJDTO
	//os dados q estao no OBJDTO é o q o USUARIO passou para
	//atualizar ou seja NOME e EMAIL NOVO,
	//e o PathVariable que é o /{ID} passado ali em cima
	//esse e o ID do usuario q queremos atualizar com os dados
	//q vieram no OBJDTO
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id){
	User obj = service.fromDTO(objDto);
	//passando o ID q veio na requisicao para o OBJ
	//o OBJ é os DADOS NOVOS q o usuario q colocar/atualizar
	obj.setId(id);
	obj = service.update(obj);
	
	return ResponseEntity.noContent().build();
	}	
}