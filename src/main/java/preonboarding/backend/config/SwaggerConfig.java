package preonboarding.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("채용공고 API 문서")
                .description("채용공고를 위한 Backend API 문서")
                .version("1.0.0")
                .contact(new Contact().email("siglee2247@gmail.com")
                        .name("BE developer")
                        .extensions(Map.of("email", "boj480@gmail.com")))
                .summary("채용공고 API")
                .termsOfService("SIGLEE")
                ;
    }

}

