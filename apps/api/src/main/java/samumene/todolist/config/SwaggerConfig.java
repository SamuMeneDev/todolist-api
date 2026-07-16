package samumene.todolist.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configurações de exibição da documentação da API, usando o Swagger OpenAPI.
 */
@Configuration
public class SwaggerConfig {
    /**
     * Define o pacote dos controllers e o grupo da API.
     */
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("todolist-public")
                .packagesToScan("samumene.todolist.controller")
                .build();
    }
    /**
     * Dados de metadados e contato do criador da API.
     */
    @Bean
    public OpenAPI todoListOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TODO List API")
                        .description("API de TODO List construído com Spring Boot para desenvolver projeto prático de API REST com Java")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Samuel Isidro")
                                .url("https://github.com/SamuMeneDev")
                                .email("samuelsantiago2222@gmail.com")));
    }
}
