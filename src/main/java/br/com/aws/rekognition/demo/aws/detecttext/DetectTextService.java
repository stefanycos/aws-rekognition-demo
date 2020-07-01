package br.com.aws.rekognition.demo.aws.detecttext;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.aws.rekognition.demo.aws.detecttext.exception.DetectTextException;
import lombok.AllArgsConstructor;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.DetectTextRequest;
import software.amazon.awssdk.services.rekognition.model.DetectTextResponse;
import software.amazon.awssdk.services.rekognition.model.Image;
import software.amazon.awssdk.services.rekognition.model.TextDetection;
import software.amazon.awssdk.services.rekognition.model.TextTypes;

@AllArgsConstructor
@Component
public class DetectTextService {

	private RekognitionClient rekognitionClient;

	public List<String> detectText(InputStream image) {
		DetectTextResponse reponse = this.callDetectText(image);
		return this.extractDetectedText(reponse);
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
			throw new DetectTextException("Error when requesting DetectText API. " + e.getMessage());
		}
	}

}
