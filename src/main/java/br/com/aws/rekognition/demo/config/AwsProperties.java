package br.com.aws.rekognition.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Setter
@Getter
@ConfigurationProperties(prefix = "aws")
public class AwsProperties {

	private String accessKeyId;

	private String secretAccessKey;

}
