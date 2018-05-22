package br.com.users.main;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("br.com.users")
@EntityScan("br.com.users.domain")   
@EnableJpaRepositories(basePackages = {"br.com.users.domain"})
public class UserConfiguration {

	@Bean
	public AlwaysSampler defaultSampler() {
	  return new AlwaysSampler();
	}
}
