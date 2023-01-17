package com.example.tutoriales.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.tutoriales.model.domain.Tutorial;

@Repository
public interface Conection extends MongoRepository<Tutorial, String>{

}
