package com.BackendTFI.backendTFI.utils;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Sesion sesion(){
        return Sesion.getInstancia();
    }
}
