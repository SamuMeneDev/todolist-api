package samumene.todolist.dto.response;

import samumene.todolist.enumeration.StatusTarefa;

import java.time.LocalDateTime;

public record TarefaResponse(
        Long id,
        String titulo,
        String descricao,
        CategoriaResponse categoria,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        StatusTarefa status
) {}
