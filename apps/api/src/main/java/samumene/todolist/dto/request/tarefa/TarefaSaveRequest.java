package samumene.todolist.dto.request.tarefa;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * DTO de requisição para perssistir/salvar uma nova tarefa.
 *
 * @param titulo Título da tarefa.
 * @param descricao Descrição da tarefa.
 * @param dataInicio Data de inicio da tarefa.
 * @param dataFim Data de termino da tarefa.
 * @param categoriaId Id da {@link samumene.todolist.entity.Categoria} dessa tarefa.
 */
public record TarefaSaveRequest(
        @NotBlank(message = "O titulo é obrigatório")
        String titulo,
        String descricao,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        Long categoriaId
) {}
