package br.com.aws.rekognition.demo.service;

import org.springframework.stereotype.Service;

import br.com.aws.rekognition.demo.controller.dto.ImageResponseDTO;
import br.com.aws.rekognition.demo.domains.AnalysisType;
import br.com.aws.rekognition.demo.domains.Image;
import br.com.aws.rekognition.demo.service.analysis.ImageAnalysis;
import br.com.aws.rekognition.demo.strategy.StrategyFactory;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ImageService {

	private StrategyFactory strategy;

	public ImageResponseDTO processImage(Image image) {
		return this.getStrategy(image.getType()).process(image);
	}

	private ImageAnalysis getStrategy(AnalysisType type) {
		return strategy.getStrategy(ImageAnalysis.class, type);
	}

}
