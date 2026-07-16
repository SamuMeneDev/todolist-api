package samumene.todolist.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import samumene.todolist.entity.Usuario;

import java.time.Duration;
import java.time.Instant;


/**
 * Classe de serviço que cria e valida tokens de acesso do usuário.
 */
@Service
public class TokenService {
    /**
     * Secret JWT usado na composição dos tokens.
     */
    @Value("${api.security.token.secret}")
    private String secret;
    /**
     * Metodo que gera novos tokens de acesso.
     *
     * @param usuario Objeto do usuário que requisita o token.
     * @return Retorna o token gerado.
     */
    public String generateToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
            .withIssuer("todo-list-api")
            .withClaim("roles", usuario.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
            .withSubject(usuario.getEmail())
            .withExpiresAt(Instant.now().plus(Duration.ofHours(7)))
            .sign(algorithm);
    }
    /**
     * Valida um token gerado anteriormente no sistema.
     *
     * @param token Valor do token a ser validado.
     * @return Se o token for válido, retorna o subject (email) do usuário,
     * caso contrário, retorna um string vazia {@code ""}
     */
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("todo-list-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }
}
