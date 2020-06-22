package br.com.aws.rekognition.demo.controller.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class DetectTextResponseDTO {

	private List<String> lines;

}
