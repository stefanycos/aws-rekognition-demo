package br.com.aws.rekognition.demo.controller.dto;

import org.springframework.lang.NonNull;

import br.com.aws.rekognition.demo.domains.AnalysisType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageRequestDTO {

	@NonNull
	private AnalysisType type;

	@NonNull
	private String base64;

}
