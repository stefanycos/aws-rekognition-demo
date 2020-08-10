package br.com.aws.rekognition.demo.service.analysis;

import br.com.aws.rekognition.demo.controller.dto.ImageResponseDTO;
import br.com.aws.rekognition.demo.domains.Image;
import br.com.aws.rekognition.demo.service.exception.ImageAnalysisException;

public interface ImageAnalysis {
	
	public default ImageResponseDTO execute(Image image) throws ImageAnalysisException {
		try {
			return this.process(image);
		} catch (final Exception e) {
			throw new ImageAnalysisException(e.getMessage());
		}
	}

	public abstract ImageResponseDTO process(Image image);

}
