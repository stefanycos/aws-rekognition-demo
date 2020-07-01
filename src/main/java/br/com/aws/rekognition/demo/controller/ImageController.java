package br.com.aws.rekognition.demo.controller;

import java.io.IOException;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aws.rekognition.demo.controller.dto.ImageRequestDTO;
import br.com.aws.rekognition.demo.controller.dto.ImageResponseDTO;
import br.com.aws.rekognition.demo.domains.Image;
import br.com.aws.rekognition.demo.mapper.ImageMapper;
import br.com.aws.rekognition.demo.service.ImageService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("image")
public class ImageController {

	private ImageService imageService;

	private ImageMapper mapper;

	@PostMapping
	public ImageResponseDTO process(@Validated @RequestBody ImageRequestDTO payload) throws IOException { // NOSONAR

		Image image = mapper.toImage(payload);
		return imageService.processImage(image);

	}

}
