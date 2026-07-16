package samumene.todolist.mapper;

import java.util.List;

/** Métodos de conversão de Entidade para DTO.
 * (Entity -> DTO)
 * @param <E> Tipo da Entidade.
 * @param <D> Tipo do DTO.
 * @apiNote Essa interface, para fins de padronização, implementa o padrão LSP do SOLID, onde esta interface é parte de
 *  * uma implementação completa de conversão entre entidades de DTO.
 */
public interface EntityDTOParser<D, E> {
    D toDTO(E entity);
    List<D> toDTOList(List<E> entityList);
}
