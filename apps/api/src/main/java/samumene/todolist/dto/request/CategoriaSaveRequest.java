package samumene.todolist.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoriaSaveRequest(
    @NotBlank(message = "O título é obrigatório") String titulo,
    String descricao
) {}
