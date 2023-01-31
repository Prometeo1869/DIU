package com.example.tutoriales.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.tutoriales.model.TutorialDTO;
import com.example.tutoriales.service.TutorialService;

@CrossOrigin
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
	
	@GetMapping("/tutorial/{id}")
	public ResponseEntity<?> getTutorialById(@PathVariable String id) {
		TutorialDTO dto = service.getById(id);
		if(dto == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(dto);
		}
	}

	@GetMapping("/tutorials/published")
	public ResponseEntity<?> getTutorialsPublished() {
		List<TutorialDTO> lista = service.findByPublished(true);
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(lista);
		}
	}
	
	@PutMapping("/tutorials/{id}")
	public ResponseEntity<?> updateTutorial(@PathVariable String id, @RequestBody TutorialDTO dto) {
		TutorialDTO updating = service.updateTutorial(id, dto);
		if (updating == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(updating);
		}
	}
	
	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<?> deleteTutorial(@PathVariable String id) {
		if (service.exist(id)) {
			service.deleteTutorial(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/tutorials")
	public ResponseEntity<?> deleteAllTutorials() {
		if(service.count() > 0) {
			service.deleteAllTutorials();
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
