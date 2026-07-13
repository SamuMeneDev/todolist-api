package samumene.todolist.mapper;

import java.util.List;

/** Métodos que convertem DTOs em Entidade.
 *
 * @param <D> Tipo do DTO.
 * @param <E> Tipo da Entidade.
 */
public interface DTOEntityParser<D, E> {

    E toEntity(D dto);
    List<E> toEntityList(List<D> dtoList);

}
