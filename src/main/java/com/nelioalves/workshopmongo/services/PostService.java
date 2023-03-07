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

package com.nelioalves.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.repository.PostRepository;
import com.nelioalves.workshopmongo.services.exception.ObjectNotFoundException;

//
//classe PostService, ou seja vai ser um SERVICO responsavel por
//trabalhar com os POSTS
@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;

	
	
	//criando um metodo para buscar um POST pelo o ID
	public Post findById(String id) {
		//criando um VAR optional do tipo POST q recebe o REPO q é um OBJ do
		//tipo POSTREPOSITORY, e pelo REPO chamamos o metodo HERDADO
		//FINDONE e passamos o ID
		Optional<Post> user = repo.findById(id);
		//no RETURN, nos chamamos o nosso OBJ e nele chamamos o METODO
		//ORELSETHROW e para esse metodo passamos a classe OBJECTNOTFOUND
		//EXCEPTION com a msg objeto nao encontrado
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

}
