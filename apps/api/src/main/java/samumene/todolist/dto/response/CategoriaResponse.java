package samumene.todolist.dto.response;

import samumene.todolist.enumeration.StatusCategoria;

/**
 * DTO de resposta de {@link samumene.todolist.entity.Categoria}
 *
 * @param id Id da categoria.
 * @param titulo Título da categoria.
 * @param descricao Descrição opcional da categoria.
 * @param status Status da categoria, baseado em {@link StatusCategoria}
 */
public record CategoriaResponse(
        Long id,
        String titulo,
        String descricao,
        StatusCategoria status
) {}
