package samumene.todolist.dto.request.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginRequest(
        @Email(message = "Email inválido") String email,
        @NotBlank(message = "A senha é obrigatória") String senha
) {}
