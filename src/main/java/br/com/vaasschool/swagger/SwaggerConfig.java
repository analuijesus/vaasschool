package br.com.vaasschool.swagger;

import br.com.vaasschool.controller.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket vaasschoolApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.vaasschool.controller.api"))
                .paths(PathSelectors.ant("/api/*"))
                .build()
                .ignoredParameterTypes(User.class);
    }
}
