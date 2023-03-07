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

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.repository.UserRepository;
import com.nelioalves.workshopmongo.services.exception.ObjectNotFoundException;

//
//classe UserService, ou seja vai ser um SERVICO responsavel por
//trabalhar com os USUARIOS/USERS
@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;

	
	//criando o METODO q irá retornar os USUARIOS do BANCO
	public List<User> findAll(){
		//chamando o OBJ REPO q é do tipo USERREPOSITORY... E esse
		//USERREPOSITORY ele HERDA varios METODOS da classe 
		//MONGOREPOSITORY e assim se conecta no BANCO MONGO
		//ou seja estamos usando o SPRING DATA
		return repo.findAll();	
	}
	
	//criando um metodo para buscar um USER pelo o ID
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	//metodo para inserir USUARIO com POST
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	//implementando o metodo DELETE, q recebe um ID de argumento
	//ID do usuario q queremos deletar
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	//metodo para ATUALIZAR os dados de um USUARIO
	//recebendo um USUARIO como argumento
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	//metodo q COPIA os dados q estao no OBJ para o NEWOBJ
	//ou seja pegar o q foi passado como argumento pelo
	//sistema e passar como dados para o USUARIO ANTIGO(newobj)
	//e ASSIM DEIXAR O NEWOBJ ATUALIZADO :)
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
