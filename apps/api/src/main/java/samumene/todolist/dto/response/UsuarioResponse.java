package samumene.todolist.dto.response;

/**
 * DTO de resposta para {@link samumene.todolist.entity.Usuario}
 *
 * @param id Id do usuáio
 * @param nome Nome do usuário
 * @param email Email do usuário
 */
public record UsuarioResponse(
        Long id,
        String nome,
        String email
) {}
