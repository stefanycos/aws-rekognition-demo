package br.com.aws.rekognition.demo.service.analysis;

import java.util.List;

import br.com.aws.rekognition.demo.aws.RekognitionClientService;
import br.com.aws.rekognition.demo.controller.dto.ImageResponseDTO;
import br.com.aws.rekognition.demo.domains.AnalysisType;
import br.com.aws.rekognition.demo.domains.Image;
import br.com.aws.rekognition.demo.strategy.AnalysisStrategy;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@AnalysisStrategy(type = AnalysisType.DETECT_TEXT)
public class DetectText implements ImageAnalysis {

	private RekognitionClientService clientService;

	@Override
	public ImageResponseDTO process(Image image) {
		List<String> result = clientService.getDetectTextService().detectText(image.getFile());

		//@formatter:off
		return ImageResponseDTO.builder()
					.lines(result)
					.build(); //@formatter:off
	}

	
}
