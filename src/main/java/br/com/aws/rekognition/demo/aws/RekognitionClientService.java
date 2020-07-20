package br.com.aws.rekognition.demo.aws;

import org.springframework.stereotype.Component;

import br.com.aws.rekognition.demo.aws.services.DetectLabelService;
import br.com.aws.rekognition.demo.aws.services.DetectTextService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Component
public class RekognitionClientService {
	
	private DetectTextService detectTextService;
	
	private DetectLabelService detectLabelService;

}
