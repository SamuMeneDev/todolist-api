package samumene.todolist.mapper;

import org.mapstruct.Mapper;
import samumene.todolist.dto.response.UsuarioResponse;
import samumene.todolist.entity.Usuario;

/**
 * Realiza conversões entre a Entidade {@link Usuario} e a DTO {@link UsuarioResponse}
 */
@Mapper(componentModel = "spring")
public interface UsuarioMapper extends EntityDTOParser<UsuarioResponse, Usuario> {}
