package samumene.todolist.mapper;

import org.mapstruct.Mapper;
import samumene.todolist.dto.response.TarefaResponse;
import samumene.todolist.entity.Tarefa;

/**
 * Realiza conversões entre a Entidade {@link Tarefa} e a DTO {@link TarefaResponse}
 */
@Mapper(componentModel = "spring")
public interface TarefaMapper extends EntityDTOParser<TarefaResponse, Tarefa> {}
