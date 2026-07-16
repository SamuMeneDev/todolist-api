package samumene.todolist.dto.request.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO de requisição para autênticar um usuário
 *
 * @param email Email do usuário.
 * @param senha Senha do usuário
 */
public record UsuarioLoginRequest(
        @Email(message = "Email inválido") String email,
        @NotBlank(message = "A senha é obrigatória") String senha
) {}
