package com.example.tutoriales.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tutoriales.model.TutorialDTO;
import com.example.tutoriales.service.TutorialService;

@RestController
public class TutorialController {
	
	private final TutorialService service;

	public TutorialController(TutorialService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/tutorials")
	public ResponseEntity<?> createTutorial(@RequestBody TutorialDTO tutorial) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.createdTutorial(tutorial));
		
	}
	
	

}
