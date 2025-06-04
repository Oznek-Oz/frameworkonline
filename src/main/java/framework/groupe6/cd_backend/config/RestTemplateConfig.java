package framework.groupe6.cd_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {

        //try {
        //HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        //factory.setConnectTimeout(10000);  // 3 secondes
        //factory.setReadTimeout(10000);
        //} catch (Error e){
        //	throw new RuntimeException("Impossible de configurer le temps d'accès");
        //}

        return new RestTemplate();
    }
}
