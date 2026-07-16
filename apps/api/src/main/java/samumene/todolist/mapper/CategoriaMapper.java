package samumene.todolist.mapper;

import org.mapstruct.Mapper;
import samumene.todolist.dto.response.CategoriaResponse;
import samumene.todolist.entity.Categoria;

/**
 * Realiza conversões entre a Entidade {@link Categoria} e a DTO {@link CategoriaResponse}
 */
@Mapper(componentModel = "spring")
public interface CategoriaMapper extends EntityDTOParser<CategoriaResponse, Categoria> {}
