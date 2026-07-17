package samumene.todolist.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import samumene.todolist.security.SecurityFilter;

/**
 * Classe de configuração de segurança e autorização da API
 */
@Configuration
@EnableWebSecurity
@SecurityScheme(name = SecurityConfig.SECURITY, type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class SecurityConfig {
    // Dependencias
    private final SecurityFilter securityFilter;
    private final HandlerExceptionResolver resolver;

    // Injeção de dependência
    public SecurityConfig(SecurityFilter securityFilter, @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.securityFilter = securityFilter;
        this.resolver = resolver;
    }
    /**
     * Atributo usado para destacar autorizações no Swagger.
     */
    public static final String SECURITY = "bearerAuth";

    // Métodos
    /**
     * Metodo que configura as permissões e comportamento das requisições.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors->cors.configure(http))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // ROTAS PÚBLICAS
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuario/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuario/register").permitAll()
                        .requestMatchers("/h2-console").permitAll()

                        .anyRequest().authenticated()

                )
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) ->
                                resolver.resolveException(request, response, null, authException)
                        )
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    /**
     * Bean que aponta para a estratégia de criptografia das senhas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
