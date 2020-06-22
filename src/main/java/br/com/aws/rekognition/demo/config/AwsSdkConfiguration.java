package br.com.aws.rekognition.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;

@AllArgsConstructor
@Configuration
public class AwsSdkConfiguration {

	@Bean
	public RekognitionClient rekognitionClient(AwsProperties properties) {
		AwsCredentials credentials = AwsBasicCredentials.create(properties.getAccessKeyId(),
				properties.getSecretAccessKey());

		//@formatter:off
		
		return RekognitionClient.builder()
				.credentialsProvider(StaticCredentialsProvider.create(credentials))
				.region(Region.US_WEST_2)
				.build(); //@formatter:on
	}

}
