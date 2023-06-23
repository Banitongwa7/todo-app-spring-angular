package com.example.springback.controller;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Configure le mapping de l'API spécifique
                .allowedOrigins("http://localhost:4200") // Autorise les requêtes provenant de cette origine
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Autorise les méthodes HTTP spécifiées
                .allowedHeaders("*"); // Autorise tous les en-têtes
    }
}
