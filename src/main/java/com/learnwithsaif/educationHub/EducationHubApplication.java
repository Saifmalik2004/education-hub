package com.learnwithsaif.educationHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.learnwithsaif.educationHub.repository")
@EntityScan("com.learnwithsaif.educationHub.model")
@EnableJpaAuditing(auditorAwareRef="auditAwareImpl")
public class EducationHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducationHubApplication.class, args);
	}

}
