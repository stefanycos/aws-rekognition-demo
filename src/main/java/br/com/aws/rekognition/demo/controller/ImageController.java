package br.com.aws.rekognition.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.aws.rekognition.demo.aws.detecttext.DetectTextService;
import br.com.aws.rekognition.demo.controller.dto.DetectTextResponseDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("image")
public class ImageController {

	private DetectTextService detectTextDervice;

	@PostMapping
	public DetectTextResponseDTO detectText(@RequestParam("image") MultipartFile image) throws IOException {
		List<String> result = detectTextDervice.detectText(image.getInputStream());
		return new DetectTextResponseDTO(result);
	}

}
