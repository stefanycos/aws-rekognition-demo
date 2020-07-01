package br.com.aws.rekognition.demo.domains;

import java.io.InputStream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Image {

	private AnalysisType type;

	private InputStream file;

}
