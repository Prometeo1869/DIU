package com.example.tutoriales.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class TutorialDTO {

	private String id;
	
	private String title;
	private String description;
	private Boolean published;
}
