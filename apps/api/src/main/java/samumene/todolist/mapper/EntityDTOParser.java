package samumene.todolist.mapper;

import java.util.List;

/** Métodos de conversão de Entidade para DTO.
 *
 * @param <E> Tipo da Entidade.
 * @param <D> Tipo do DTO.
 */
public interface EntityDTOParser<D, E> {
    D toDTO(E entity);
    List<D> toDTOList(List<E> entityList);
}
