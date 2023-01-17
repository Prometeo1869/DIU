package com.example.tutoriales.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.tutoriales.model.TutorialDTO;
import com.example.tutoriales.model.domain.Tutorial;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TutorialConvert {
	
	private ModelMapper mapper;
	
	public TutorialDTO convertTo(Tutorial tutorial) {
		return mapper.map(tutorial, TutorialDTO.class);
	}
	
	public Tutorial convertTo(TutorialDTO dto) {
		return mapper.map(dto, Tutorial.class);
	}

}
