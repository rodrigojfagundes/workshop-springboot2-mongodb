package com.nelioalves.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.repository.PostRepository;
import com.nelioalves.workshopmongo.repository.UserRepository;

//classe  para ADD dados do tipo USER e POST no
//BANCO MONGO... q implementa a INTERFACE ComandLineRunner
	//@configuration para o SPRING DATA reconhecer essa classe
	//como uma configuracao
@Configuration
public class Instantiation implements CommandLineRunner {
	
	
	//o @AUTOWIRED e para instanciar(injetar depedencias)
	//um OBJ automaticamente...  no caso sera um OBJ do 
	//tipo USERREPOSITORY, q vamos chamar de userRepository
	@Autowired
	private UserRepository userRepository;
	
	//o @AUTOWIRED e para instanciar(injetar depedencias)
	//um OBJ automaticamente...  no caso sera um OBJ do 
	//tipo POSTREPOSITORY, q vamos chamar de postRepository
	@Autowired
	private PostRepository postRepository;
	
	
	
	//metodo RUN
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		//antes de INSERIR novos usuarios e post, vamos deletar 
		//os q ja tiver cadastrado no BANCO, para ficar MAIS 
		//ORGANIZADO com o metodo DeleteALL
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		
		
		//instanciando 3 OBJ do tipo USER
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		
		
		
		//criando um OBJ do tipo POST, chamado de POST1
		//passando ID null... Data... Titulo do post... e corpo...
		//e o nome do AUTHOR da POSTAGEM
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", maria);
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", maria);
		
		//o OBJ userRepository, e como ele representa a CLASSE
		//USERREPOSITORY e essa classe HERDA OS METODOS do MongoRepository
		//dai nos chamamos o metodo SaveAll e salvamos os OBJ
		//do tipo USER maria, alex, bob no BANCO
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		//o POSTREPOSITORY é semelhante ao USERREPOSITORY acima
		postRepository.saveAll(Arrays.asList(post1, post2));
		
	}

}
