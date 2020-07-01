package br.com.aws.rekognition.demo.service.analysis;

import br.com.aws.rekognition.demo.controller.dto.ImageResponseDTO;
import br.com.aws.rekognition.demo.domains.Image;

public interface ImageAnalysis {

	public ImageResponseDTO process(Image image);

}
