package samumene.todolist.dto.request.categoria;

import jakarta.validation.constraints.Pattern;
import samumene.todolist.enumeration.StatusCategoria;

/**
 * DTO de requisição para mudar o status de uma {@link samumene.todolist.entity.Categoria}
 * @param status status alterado da categoria, baseado em {@link StatusCategoria}
 */
public record CategoriaChangeStatusRequest(
        @Pattern(regexp = StatusCategoria.statusCategoriaRegExp, message = "Status inválido")
        String status
) {}
