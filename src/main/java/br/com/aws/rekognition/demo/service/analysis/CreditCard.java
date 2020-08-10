package br.com.aws.rekognition.demo.service.analysis;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.aws.rekognition.demo.aws.services.DetectTextService;
import br.com.aws.rekognition.demo.controller.dto.ImageResponseDTO;
import br.com.aws.rekognition.demo.domains.AnalysisType;
import br.com.aws.rekognition.demo.domains.CreditCardBrand;
import br.com.aws.rekognition.demo.domains.Image;
import br.com.aws.rekognition.demo.strategy.AnalysisStrategy;
import br.com.aws.rekognition.demo.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@AnalysisStrategy(type = AnalysisType.CREDIT_CARD)
public class CreditCard implements ImageAnalysis {

	private DetectTextService detectTextService;

	@Override
	public ImageResponseDTO process(Image image) {
		log.info("Starting image analysis. API Detect Text");
		List<String> result = detectTextService.detectText(image.getFile());

		String number = this.getNumber(result);

		CreditCardBrand brand = this.getBrand(number);

		//@formatter:off
		return ImageResponseDTO.builder()
					.number(number)
					.brand(brand)
					.build(); //@formatter:off
	}

	private CreditCardBrand getBrand(String number) {
		CreditCardBrand[] brands = CreditCardBrand.values();

		for (int i = 0; i < brands.length; i++) {
			Pattern brandPattern = Pattern.compile(brands[i].getPattern());
			Matcher matcher = this.removeWhiteSpaces(number, brandPattern);

			if (matcher.matches()) {
				return brands[i];
			}
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit card number not found.");
	}

	private Matcher removeWhiteSpaces(String number, Pattern brandPattern) {
		return brandPattern.matcher(number.replaceAll("\\s", ""));
	}

	private String getNumber(List<String> lines) {
		Pattern creditCardPattern = Pattern.compile(Constants.CREDIT_CARD_PATTERN);

		for (String line : lines) {

			Matcher matcher = creditCardPattern.matcher(line);
			if (matcher.matches()) {
				return line;
			}

		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit card brand not found.");
	}

}
