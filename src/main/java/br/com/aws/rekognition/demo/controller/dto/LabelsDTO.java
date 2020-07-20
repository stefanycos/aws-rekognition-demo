package br.com.aws.rekognition.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class LabelsDTO {
	
	private String text;
	
	private Float confidence;

}
