package samumene.todolist.dto.response;

import java.time.LocalDateTime;

/**
 * DTO de resposta para o retorno de tokens de acessos gerados no sistema.
 *
 * @param token Token JWT.
 * @param email Subject (email) do usuário que se autênticou.
 * @param timestamp Data de geração.
 */
public record TokenResponse(
        String token,
        String email,
        LocalDateTime timestamp
) {}
