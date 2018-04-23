package com.adun.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompanyApplication {
	private static final Logger logger = LoggerFactory.getLogger(CompanyApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CompanyApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
		logger.info("SpringBoot server stated on port: 9000");
	}
}
