package br.com.aws.rekognition.demo.mapper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

import org.springframework.stereotype.Component;

import br.com.aws.rekognition.demo.controller.dto.ImageRequestDTO;
import br.com.aws.rekognition.demo.domains.Image;

@Component
public class ImageMapper {

	public Image toImage(ImageRequestDTO request) {
		InputStream file = loadInputStream(request.getBase64());

		return new Image(request.getType(), file);
	}

	private InputStream loadInputStream(String base64) {
		byte[] bytes = Base64.getDecoder().decode(base64);
		return new ByteArrayInputStream(bytes);
	}
}
