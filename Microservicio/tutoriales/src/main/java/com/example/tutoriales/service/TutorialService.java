package com.example.tutoriales.service;

import java.util.List;

import com.example.tutoriales.model.TutorialDTO;

public interface TutorialService {

	TutorialDTO createdTutorial(TutorialDTO tutorial);

	List<TutorialDTO> getByTitle(String title);

	List<TutorialDTO> getTutoriales();

	TutorialDTO getById(String id);

	List<TutorialDTO> findByPublished(boolean b);

	TutorialDTO updateTutorial(String id, TutorialDTO dto);

	boolean exist(String id);

	void deleteTutorial(String id);
	
	public long count();

	void deleteAllTutorials();
	
	
}
