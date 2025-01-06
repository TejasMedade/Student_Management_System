/**
 * SwaggerConfig configures the OpenAPI specification for Swagger UI,
 * providing detailed API documentation for the Student Management System.
 *
 * @author Tejas_Medade
 */
package com.synchrony.configurations.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The SwaggerConfig class is responsible for setting up Swagger API documentation
 * for the Student Management System. It defines the OpenAPI configuration, including
 * title, description, version, and license information.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configures the OpenAPI specification for Swagger UI. This method sets up the
     * basic information about the API such as title, description, version, and license.
     *
     * @return an OpenAPI instance with application-specific details for Swagger UI.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()) // Components section can be expanded with reusable schemas and security schemes
                .info(new Info()
                        .title("Student Management System API") // API title
                        .description("API documentation for the Student Management System") // API description
                        .version("1.0.0") // API version
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))); // License information
    }
}
