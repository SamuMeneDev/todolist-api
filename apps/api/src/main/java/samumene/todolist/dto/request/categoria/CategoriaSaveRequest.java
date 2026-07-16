package samumene.todolist.dto.request.categoria;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO de requisição para perssistir/salvar uma nova categoria.
 *
 * @param titulo Título da categoria.
 * @param descricao Descrição opcional da categoria.
 */
public record CategoriaSaveRequest(
    @NotBlank(message = "O título é obrigatório") String titulo,
    String descricao
) {}
