package br.com.aws.rekognition.demo.strategy;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import br.com.aws.rekognition.demo.domains.AnalysisType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class StrategyFactory {

	private final ApplicationContext applicationContext;
	
	public <T> T getStrategy(Class<T> strategyType, AnalysisType type) {
		final Map<String, T> beans = applicationContext.getBeansOfType(strategyType);
		
		for (final T strategy : beans.values()) {
			final AnalysisStrategy annotation = AnnotationUtils.findAnnotation(strategy.getClass(), AnalysisStrategy.class);
			
			if (annotation != null && annotation.type() != null && annotation.type().equals(type)) {
				return strategy;
			}
		}
		
		return null;
	}
}
