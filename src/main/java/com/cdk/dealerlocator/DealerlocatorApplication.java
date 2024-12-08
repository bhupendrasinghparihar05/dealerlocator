package com.cdk.dealerlocator;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication
public class DealerlocatorApplication {
	@Value("${spring.datasource.url}")
	private String datasourceUrl;

	public static void main(String[] args) {
		SpringApplication.run(DealerlocatorApplication.class, args);
	}

	@PostConstruct
	public void logDatabaseUrl() {
		System.out.println("Connecting to MySQL with URL: " + datasourceUrl);
	}
}
