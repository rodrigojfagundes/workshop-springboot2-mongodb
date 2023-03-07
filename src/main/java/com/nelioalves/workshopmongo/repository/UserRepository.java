package com.nelioalves.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.workshopmongo.domain.User;

//
//interface USERREPOSITORY
//REPOSITORY... os repository fazem OPERACOES basicas de ACESSO AO BANCO
//
@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
