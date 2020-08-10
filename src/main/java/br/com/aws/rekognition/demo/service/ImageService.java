package br.com.aws.rekognition.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.aws.rekognition.demo.controller.dto.ImageResponseDTO;
import br.com.aws.rekognition.demo.domains.AnalysisType;
import br.com.aws.rekognition.demo.domains.Image;
import br.com.aws.rekognition.demo.service.analysis.ImageAnalysis;
import br.com.aws.rekognition.demo.service.exception.ImageAnalysisException;
import br.com.aws.rekognition.demo.strategy.StrategyFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class ImageService {

	private StrategyFactory strategy;

	public ImageResponseDTO processImage(Image image) {
		try {
			return this.getStrategy(image.getType()).execute(image);
		} catch (final ImageAnalysisException e) {
			log.error("An error occurred on image analysis {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	public ImageAnalysis getStrategy(AnalysisType type) {
		return strategy.getStrategy(ImageAnalysis.class, type);
	}

}
