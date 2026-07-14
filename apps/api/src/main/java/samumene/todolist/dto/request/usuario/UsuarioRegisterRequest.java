package samumene.todolist.dto.request.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRegisterRequest(
        @NotBlank(message = "O nome é obrigatório") String nome,
        @Email(message = "Insira um email válido") String email,
        @NotBlank(message = "A senha é obrigatória") String senha
) {}
