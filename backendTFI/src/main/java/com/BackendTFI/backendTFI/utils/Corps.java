package com.BackendTFI.backendTFI.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Corps {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Define aquí la ruta para la que deseas habilitar CORS
                        .allowedOrigins("*") // Permite todas las solicitudes de cualquier origen
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Define aquí los métodos permitidos
                        .allowedHeaders("*"); // Permite todos los encabezados
            }
        };
    }
}
