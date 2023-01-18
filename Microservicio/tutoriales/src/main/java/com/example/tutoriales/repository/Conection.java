package com.example.tutoriales.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.tutoriales.model.TutorialDTO;
import com.example.tutoriales.model.domain.Tutorial;

@Repository
public interface Conection extends MongoRepository<Tutorial, String>{

	List<Tutorial> findByTitle(String title);

}
