package br.com.aws.rekognition.demo.service.analysis;

import java.util.List;

import br.com.aws.rekognition.demo.aws.RekognitionClientService;
import br.com.aws.rekognition.demo.controller.dto.ImageResponseDTO;
import br.com.aws.rekognition.demo.controller.dto.LabelsDTO;
import br.com.aws.rekognition.demo.domains.AnalysisType;
import br.com.aws.rekognition.demo.domains.Image;
import br.com.aws.rekognition.demo.strategy.AnalysisStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@AnalysisStrategy(type = AnalysisType.LABELS)
public class DetectLabels implements ImageAnalysis {

	private RekognitionClientService clientService;

	@Override
	public ImageResponseDTO process(Image image) {
		log.info("Starting image analysis. API Detect Labels");
		List<LabelsDTO> result = clientService.getDetectLabelService().detectText(image.getFile());

		//@formatter:off
		return ImageResponseDTO.builder()
					.labels(result)
					.build(); //@formatter:off
	}

	
}
