package samumene.todolist.dto.request.categoria;

import jakarta.validation.constraints.NotBlank;

public record CategoriaEditRequest(
        @NotBlank(message = "O titlo é obrigatório")
        String titulo,
        String descricao
) {}
