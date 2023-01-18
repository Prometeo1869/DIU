package com.example.tutoriales.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tutoriales.model.TutorialDTO;
import com.example.tutoriales.service.TutorialService;

@RestController
public class TutorialController {
	
	private final TutorialService service;

	public TutorialController(TutorialService service) {
		this.service = service;
	}
	
	@PostMapping("/tutorial")
	public ResponseEntity<?> createTutorial(@RequestBody TutorialDTO tutorial) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.createdTutorial(tutorial));
		
	}
	
	@GetMapping("/tutorials/{title}")
	public ResponseEntity<?> findByTitleContaining(@PathVariable String title) {
		List<TutorialDTO> lista = service.getByTitle(title);
		if (lista == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(lista);
		}
	}
	
	@GetMapping("/tutorials")
	public ResponseEntity<?> obtenerTodos() {
		List<TutorialDTO> lista = service.getTutoriales();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(lista);
		}
	}
	

}
