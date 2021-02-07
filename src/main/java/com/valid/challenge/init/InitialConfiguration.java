package com.valid.challenge.init;

import javax.sql.DataSource;

import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jooq.JooqExceptionTranslator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.valid.challenge.controller.UserRepository;

@Configuration
public class InitialConfiguration {

	@Autowired
	private DataSource dataSource;

	@Bean
	public DataSourceConnectionProvider connectionProvider() {
	    return new DataSourceConnectionProvider
	      (new TransactionAwareDataSourceProxy(dataSource));
	}
	
	@Bean
	public DefaultDSLContext dsl() {
	    return new DefaultDSLContext(configuration());
	}
	
	@Bean
	public UserRepository repository() {
	    return new UserRepository();
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurer() {
	                @Override
	                public void addCorsMappings(CorsRegistry registry) {
	                        registry.addMapping("/valid/users/**")
	                                .allowedOrigins("*")
	                                .allowedMethods("GET", "POST", "OPTIONS")
	                                .maxAge(3600);
	                }

	        };
	}
	
	public DefaultConfiguration configuration() {
	    DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
	    jooqConfiguration.set(connectionProvider());
	    jooqConfiguration.set(new DefaultExecuteListenerProvider(new JooqExceptionTranslator()));
	    return jooqConfiguration;
	}
}
