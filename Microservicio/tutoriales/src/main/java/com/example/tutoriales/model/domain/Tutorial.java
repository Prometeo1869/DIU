package com.example.tutoriales.model.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Document(collection = "tutoriales")
@JsonPropertyOrder({"id", "title", "description", "published"})
@Data
public class Tutorial {

	@Id
	private String id;
	
	private String title;
	private String description;
	private Boolean published;
	
}
