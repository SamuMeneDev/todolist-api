package samumene.todolist.dto.request.tarefa;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * DTO de requisição para editar uma tarefa.
 *
 * @param titulo Título da tarefa.
 * @param descricao Descrição da tarefa.
 * @param dataInicio Data de início da tarefa.
 * @param dataFim Data de término da tarefa.
 * @param categoriaId Id da {@link samumene.todolist.entity.Categoria} dessa tarefa.
 */
public record TarefaEditRequest(
        @NotBlank(message = "O titulo da tarefa é obrigatório")
        String titulo,
        String descricao,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        Long categoriaId
) {}
