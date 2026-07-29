package com.fleetos.vms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Vehicle Management System.
 *
 * On startup this exposes:
 *   - the web app          → http://localhost:8080/
 *   - the REST API         → http://localhost:8080/api/vehicles
 *   - Swagger UI           → http://localhost:8080/swagger-ui/index.html
 *   - raw OpenAPI JSON     → http://localhost:8080/v3/api-docs
 *   - H2 database console  → http://localhost:8080/h2-console
 */
@SpringBootApplication
public class VmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(VmsApplication.class, args);
    }
}
