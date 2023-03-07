package com.nelioalves.workshopmongo.resources;

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
	//
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	//o ResponseEntity e para retornar RESPOSTAS em HTTP ja com
	//cabecalhos, etc... q retorna APENAS 1 POST e NÂO uma LIST
	//o @PATHVARIABLE e para dizer q o ID do argumento e o ID passado
	//ali em cima no value="/{id}"
public ResponseEntity<Post> findById(@PathVariable String id){
	Post obj = service.findById(id);
	return ResponseEntity.ok().body(obj);
	}	
	@RequestMapping(value="/titlesearch", method=RequestMethod.GET)


	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text){
	text = URL.decodeParam(text);
	List<Post> list = service.findByTitle(text);
	return ResponseEntity.ok().body(list);
	}
}
