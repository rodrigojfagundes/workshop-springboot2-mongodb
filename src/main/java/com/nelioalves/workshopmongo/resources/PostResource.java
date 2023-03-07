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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.services.PostService;


@RestController
	//colocando o @RequestMapping para dizer qual e o caminho
	//do ENDPOINT... Ou seja o caminho q sera solicitado pelo
	//navegador... ex localhost:8080/posts
@RequestMapping(value="/posts")
public class PostResource {
	@Autowired
	private PostService service;

	//criando um metodo FINDBYID q chama o POSTSERVICE e pede para
	//para retornar o POST por ID conforme estao CAD no BANCO
	//	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	//o ResponseEntity e para retornar RESPOSTAS em HTTP ja com
	//cabecalhos, etc... q retorna APENAS 1 POST e NÂO uma LIST
	//o @PATHVARIABLE e para dizer q o ID do argumento e o ID passado
	//ali em cima no value="/{id}"
public ResponseEntity<Post> findById(@PathVariable String id){
	
	//instanciando um OBJ do tipo POST recebendo o valor
	//do SERVICE.findById, e passando como parametro
	//o STRING ID q recebemos ali no metodo acima String id...
	Post obj = service.findById(id);
	
	//retornando um ResponseEntity OK, para dizer q a resposta
	//ocorreu com sucesso...
	//e no BODY no vamos passar o nosso OBJ do tipo POST
	return ResponseEntity.ok().body(obj);
}	
}
