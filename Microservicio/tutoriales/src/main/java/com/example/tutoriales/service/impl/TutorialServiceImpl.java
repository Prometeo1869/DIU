package com.example.tutoriales.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tutoriales.model.TutorialDTO;
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

	

}
