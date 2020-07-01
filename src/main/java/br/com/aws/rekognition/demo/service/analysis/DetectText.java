package br.com.aws.rekognition.demo.service.analysis;

import java.util.List;

import br.com.aws.rekognition.demo.aws.detecttext.DetectTextService;
import br.com.aws.rekognition.demo.controller.dto.ImageResponseDTO;
import br.com.aws.rekognition.demo.domains.AnalysisType;
import br.com.aws.rekognition.demo.domains.Image;
import br.com.aws.rekognition.demo.strategy.AnalysisStrategy;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@AnalysisStrategy(type = AnalysisType.DETECT_TEXT)
public class DetectText implements ImageAnalysis {

	private DetectTextService detectTextService;

	@Override
	public ImageResponseDTO process(Image image) {
		List<String> result = detectTextService.detectText(image.getFile());

		//@formatter:off
		return ImageResponseDTO.builder()
					.lines(result)
					.build(); //@formatter:off
	}

	
}
