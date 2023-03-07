
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

package com.nelioalves.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.workshopmongo.domain.Post;

//
//interface POSTREPOSITORY
//REPOSITORY... os repository fazem OPERACOES basicas de ACESSO AO BANCO
@Repository
//a INTERFACE POSTREPOSITORY vai HERDAR da INTERFACE ou CLASSE nao sei
//MongoRepository q ja existe no SPRINGDATA... e passando q sera recebido
//dados do TIPO POST e q o TIPO do ID(do post) é do tipo STRING
public interface PostRepository extends MongoRepository<Post, String>{

}
