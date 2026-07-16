package samumene.todolist.mapper;

import java.util.List;

/** Métodos que convertem DTOs em Entidade.
 * (DTO -> Entity)
 * @param <D> Tipo do DTO.
 * @param <E> Tipo da Entidade.
 * @apiNote Essa interface, para fins de padronização, implementa o padrão LSP do SOLID, onde esta interface é parte de
 *  *  * uma implementação completa de conversão entre entidades de DTO.
 */
public interface DTOEntityParser<D, E> {

    E toEntity(D dto);
    List<E> toEntityList(List<D> dtoList);

}
