package br.com.aws.rekognition.demo.aws.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.aws.rekognition.demo.aws.exception.RekognitionAnalysisException;
import br.com.aws.rekognition.demo.controller.dto.LabelsDTO;
import lombok.AllArgsConstructor;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.DetectLabelsRequest;
import software.amazon.awssdk.services.rekognition.model.DetectLabelsResponse;
import software.amazon.awssdk.services.rekognition.model.Image;

@AllArgsConstructor
@Component
public class DetectLabelService {

	private RekognitionClient rekognitionClient;

	public List<LabelsDTO> detectText(InputStream image) {

		DetectLabelsResponse response = this.callDetectLabel(image);

		return this.extractLabels(response);
	}

	private List<LabelsDTO> extractLabels(DetectLabelsResponse response) {
		List<LabelsDTO> result = new ArrayList<>(); //@formatter:off

		response.labels().forEach(label -> 
			result.add(new LabelsDTO(label.name(), label.confidence()))
		);

		return result; //@formatter:on
	}

	private DetectLabelsResponse callDetectLabel(InputStream input) {

		Image image = Image
				.builder() //@formatter:off
				.bytes(SdkBytes.fromInputStream(input))
				.build();
		
		try {
			DetectLabelsRequest request = DetectLabelsRequest.builder().
					image(image)
					.build(); //@formatter:on

			return rekognitionClient.detectLabels(request);
		} catch (final Exception e) {
			throw new RekognitionAnalysisException("Error when requesting DetectLabels API. " + e.getMessage());
		}
	}

}
