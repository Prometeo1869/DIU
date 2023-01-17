package com.example.tutoriales.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TutorialDTO {

	String id;
	
	String title;
	String description;
	Boolean published;
}
