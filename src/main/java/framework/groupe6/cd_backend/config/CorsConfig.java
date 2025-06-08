package framework.groupe6.cd_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //WebMvcConfigurer.super.addCorsMappings(registry);
                registry.addMapping("/**")
                        .allowedOrigins("http://192.168.248.135:5173",
                                "http://localhost:5173",
                                "https://tpe-framework-groupe10-16-convertonli.netlify.app")
                        .allowedMethods("GET")
                        .allowedHeaders("*");
            }
        };
    }
}
