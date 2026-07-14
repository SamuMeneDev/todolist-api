package samumene.todolist.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoriaEditRequest(
        @NotBlank(message = "O titlo é obrigatório")
        String titulo,
        String descricao
) {}
