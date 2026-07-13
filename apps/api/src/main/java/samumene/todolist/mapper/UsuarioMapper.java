package samumene.todolist.mapper;

import org.mapstruct.Mapper;
import samumene.todolist.dto.response.UsuarioResponse;
import samumene.todolist.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends BaseMapper<UsuarioResponse, Usuario> {}
