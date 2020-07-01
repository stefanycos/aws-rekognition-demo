package br.com.aws.rekognition.demo.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.aws.rekognition.demo.domains.CreditCardBrand;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Builder
@Setter
@Getter
public class ImageResponseDTO {

	private List<String> lines;

	private String number;

	private CreditCardBrand brand;

}
