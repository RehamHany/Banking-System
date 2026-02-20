package com.banking.project;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Banking System",
                description = "java Rest APIS for banking system",
                version = "v1.0",
                contact = @Contact(
                        name = "Reham Hany",
                        email = "rehamhany01@gmail.com",
                        url = "https://github.com/RehamHany/Banking-System"
                ),
                license = @License(
                        name = "Roma Hany",
                        url = "https://github.com/RehamHany/Banking-System"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "java Rest APIS for banking system",
                url = "https://github.com/RehamHany/Banking-System"
        )
)
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
