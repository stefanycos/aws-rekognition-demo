package br.com.aws.rekognition.demo.aws.services;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.aws.rekognition.demo.aws.exception.RekognitionAnalysisException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.DetectTextRequest;
import software.amazon.awssdk.services.rekognition.model.DetectTextResponse;
import software.amazon.awssdk.services.rekognition.model.Image;
import software.amazon.awssdk.services.rekognition.model.TextDetection;
import software.amazon.awssdk.services.rekognition.model.TextTypes;

@Slf4j
@AllArgsConstructor
@Component
public class DetectTextService {

	private RekognitionClient rekognitionClient;

	public List<String> detectText(InputStream image) {
		log.info("Calling Rekognition - API Detect Text");
		DetectTextResponse response = this.callDetectText(image);
		return this.extractDetectedText(response);
	}

	private List<String> extractDetectedText(DetectTextResponse response) {
		if (!response.hasTextDetections()) {
			return Collections.emptyList();
		}

		return response.textDetections()
				.stream() //@formatter:off
					.filter(text -> text.type() == TextTypes.LINE)
					.map(TextDetection::detectedText)
					.collect(Collectors.toList()); //@formatter:on
	}

	private DetectTextResponse callDetectText(InputStream input) {

		Image image = Image
				.builder() //@formatter:off
				.bytes(SdkBytes.fromInputStream(input))
				.build();
		
		try {
			DetectTextRequest request = DetectTextRequest.builder().
					image(image)
					.build(); //@formatter:on

			return rekognitionClient.detectText(request);
		} catch (final Exception e) {
			throw new RekognitionAnalysisException("Error when requesting DetectText API. " + e.getMessage());
		}
	}

}
