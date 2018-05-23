package br.com.clicks.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"br.com.clicks.domain"})
@ComponentScan("br.com.clicks")
@EntityScan("br.com.clicks.domain") 
public class ApplicationConfigTest {

}
