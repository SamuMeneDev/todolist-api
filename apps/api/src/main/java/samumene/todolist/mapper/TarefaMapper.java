package samumene.todolist.mapper;

import org.mapstruct.Mapper;
import samumene.todolist.dto.response.TarefaResponse;
import samumene.todolist.entity.Tarefa;

@Mapper(componentModel = "spring")
public interface TarefaMapper extends EntityDTOParser<TarefaResponse, Tarefa> {}
