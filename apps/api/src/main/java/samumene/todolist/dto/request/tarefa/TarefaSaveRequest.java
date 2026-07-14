package samumene.todolist.dto.request.tarefa;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record TarefaSaveRequest(
        @NotBlank(message = "O titulo é obrigatório")
        String titulo,
        String descricao,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        Long categoriaId
) {}
