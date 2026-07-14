package samumene.todolist.dto.request.categoria;

import jakarta.validation.constraints.Pattern;
import samumene.todolist.enumeration.StatusCategoria;

public record CategoriaChangeStatusRequest(
        @Pattern(regexp = StatusCategoria.statusCategoriaRegExp, message = "Status inválido")
        String status
) {}
