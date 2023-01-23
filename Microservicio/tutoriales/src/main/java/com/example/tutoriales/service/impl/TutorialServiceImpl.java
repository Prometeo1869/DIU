package com.example.tutoriales.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tutoriales.model.TutorialDTO;
import com.example.tutoriales.model.domain.Tutorial;
import com.example.tutoriales.repository.Conection;
import com.example.tutoriales.service.TutorialService;
import com.example.tutoriales.util.TutorialConvert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TutorialServiceImpl implements TutorialService {

	@Autowired
	private final Conection conn;
	
	private final TutorialConvert mapper;
	
	@Override
	public TutorialDTO createdTutorial(TutorialDTO tutorial) {
		return mapper.convertToDTO(conn.save(mapper.convertTo(tutorial)));
	}

	@Override
	public List<TutorialDTO> getByTitle(String title) {
		return conn.findByTitle(title).stream().map(mapper::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public List<TutorialDTO> getTutoriales() {
		return conn.findAll().stream().map(mapper::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public TutorialDTO getById(String id) {
		Tutorial tutorial = conn.findById(id).orElse(null);
		return tutorial != null ? mapper.convertToDTO(tutorial) : null;
	}

	@Override
	public List<TutorialDTO> findByPublished(boolean b) {
		return conn.findByPublished(b).stream().map(mapper::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public TutorialDTO updateTutorial(String id, TutorialDTO dto) {
		Tutorial updating = conn.findById(id).orElse(null);
		if (updating == null) {
			return null;
		} else {
			updating.setTitle(dto.getTitle());
			updating.setDescription(dto.getDescription());
			updating.setPublished(dto.getPublished());
			conn.save(updating);
			return mapper.convertToDTO(updating);
		}
	}

	@Override
	public void deleteTutorial(String id) {
		conn.deleteById(id);
	}

	@Override
	public void deleteAllTutorials() {
		conn.deleteAll();
	}
	
	@Override
	public boolean exist(String id) {
		return conn.existsById(id);
	}

	@Override
	public long count() {
		return conn.count();
	}

}
