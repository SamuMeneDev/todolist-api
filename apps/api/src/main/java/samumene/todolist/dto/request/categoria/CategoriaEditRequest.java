package samumene.todolist.dto.request.categoria;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO de requisição para editar uma categoria.
 *
 * @param titulo Titulo da categoria.
 * @param descricao Descrição da categoria
 */
public record CategoriaEditRequest(
        @NotBlank(message = "O titlo é obrigatório")
        String titulo,
        String descricao
) {}
