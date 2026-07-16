package samumene.todolist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private Contact contact() {
        return new Contact(
            "Samuel Isidro",
            "https://github.com/SamuMeneDev",
            "samuelsantiago2222@gmail.com"
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .description("API de TODO List construido com Spring Boot para desenvolver projeto prático de API REST com Java")
            .title("TODO List API")
            .version("1.0.0")
            .contact(this.contact())
            .build();
    }


    @Bean
    public Docket detalheApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("samumene.todolist.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfo())
                .consumes(new HashSet<>(List.of("application/json")))
                .produces(new HashSet<>(List.of("application/json")));
    }
}
