package com.fleetos.vms.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI vmsOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Vehicle Management System API")
                .version("1.0.0")
                .description("""
                        REST API for managing a vehicle fleet.

                        The Vehicle hierarchy behind this API demonstrates the core Java OOP
                        concepts end to end:
                        Class, Object, Constructor, Encapsulation, Inheritance,
                        Polymorphism, Abstraction and Interface (Chargeable).
                        """)
                .contact(new Contact().name("Sadhvika")));
    }
}
