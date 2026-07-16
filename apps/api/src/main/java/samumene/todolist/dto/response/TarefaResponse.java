package samumene.todolist.dto.response;

import samumene.todolist.enumeration.StatusTarefa;

import java.time.LocalDateTime;

/**
 * DTO de resposta para {@link samumene.todolist.entity.Tarefa} de um usuário
 *
 * @param id Id da Tarefa.
 * @param titulo Título da tarefa.
 * @param descricao Descrição opcional da tarefa.
 * @param categoria Categoria opcional da tarefa.
 * @param dataInicio Data de início da tarefa.
 * @param dataFim Data de termino da tarefa.
 * @param status Status da tarefa, baseado em {@link StatusTarefa}
 */
public record TarefaResponse(
        Long id,
        String titulo,
        String descricao,
        CategoriaResponse categoria,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        StatusTarefa status
) {}
