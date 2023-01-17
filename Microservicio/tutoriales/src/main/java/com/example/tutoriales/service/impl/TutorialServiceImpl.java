package com.example.tutoriales.service.impl;

import org.springframework.stereotype.Service;

import com.example.tutoriales.model.TutorialDTO;
import com.example.tutoriales.repository.Conection;
import com.example.tutoriales.service.TutorialService;
import com.example.tutoriales.util.TutorialConvert;

@Service
public class TutorialServiceImpl implements TutorialService {

	private Conection conn;
	private TutorialConvert mapper;
	
	@Override
	public TutorialDTO createdTutorial(TutorialDTO tutorial) {
		return mapper.convertTo(conn.save(mapper.convertTo(tutorial)));
	}

}
