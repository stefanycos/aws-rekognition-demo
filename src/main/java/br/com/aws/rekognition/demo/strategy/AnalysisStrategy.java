package br.com.aws.rekognition.demo.strategy;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

import br.com.aws.rekognition.demo.domains.AnalysisType;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface AnalysisStrategy {

	AnalysisType type();
}
