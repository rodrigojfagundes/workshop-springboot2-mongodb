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

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.resources.util.URL;
import com.nelioalves.workshopmongo.services.PostService;

//classe POSTRESOURCE dentro do SUBPACOTE RESOURCES,
//o nome é RESOURCE pois o nome tecnico para referenciar
//RECURSOS REST é RESOURCE... Ou seja são RECURSOS q o BACKEND
//vai DISPONIBILIZAR... tbm pd ser chamado de 
//CONTROLLER/CONTROLADOR
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
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
public ResponseEntity<Post> findById(@PathVariable String id){
	Post obj = service.findById(id);
	
	//retornando um ResponseEntity OK, para dizer q a resposta
	//ocorreu com sucesso...
	return ResponseEntity.ok().body(obj);
	}	
	
	
	
	//implementando o METODO/ENDPOINT para fazermos a
	//busca por alguma palavra especifica no titulo dos POST
	@RequestMapping(value="/titlesearch", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text){
	text = URL.decodeParam(text);

	List<Post> list = service.findByTitle(text);
	return ResponseEntity.ok().body(list);
	}
	
	
	
	//implementando o METODO/ENDPOINT para fazermos a
		//busca por alguma palavra especifica EM TODOS OS LOCAIS
		@RequestMapping(value="/fullsearch", method=RequestMethod.GET)
		public ResponseEntity<List<Post>> fullSearch(
				//passando os 3 parametros...
				@RequestParam(value="text", defaultValue="") String text,
				@RequestParam(value="minDate", defaultValue="") String minDate,
				@RequestParam(value="maxDate", defaultValue="") String maxDate)
		{
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());

		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
		}	
}