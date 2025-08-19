package com.springboot.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfig {


    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .info(new Info().title("Demo service").version("1.0.0"));


    }

    // http://localhost:8080/v2/api-docs
    //http://localhost:8080/swagger-ui.html
}
