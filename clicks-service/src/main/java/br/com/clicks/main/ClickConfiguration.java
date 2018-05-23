package br.com.clicks.main;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"br.com.clicks.domain"})
@ComponentScan("br.com.clicks")
@EntityScan("br.com.clicks.domain")
public class ClickConfiguration {

	@Bean
	public AlwaysSampler defaultSampler() {
	  return new AlwaysSampler();
	}
}
