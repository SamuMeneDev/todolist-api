package samumene.todolist.dto.response;

import samumene.todolist.enumeration.StatusCategoria;

public record CategoriaResponse(
        Long id,
        String titulo,
        String descricao,
        StatusCategoria status
) {}
