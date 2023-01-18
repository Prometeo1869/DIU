package com.example.tutoriales.service;

import java.util.List;

import com.example.tutoriales.model.TutorialDTO;

public interface TutorialService {

	TutorialDTO createdTutorial(TutorialDTO tutorial);

	List<TutorialDTO> getByTitle(String title);

	List<TutorialDTO> getTutoriales();
	
	
}
