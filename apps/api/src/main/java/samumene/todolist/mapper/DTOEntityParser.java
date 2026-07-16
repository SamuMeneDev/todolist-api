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
    /**
     * Converte uma DTO do tipo {@link D} para uma entidade do tipo {@link E}
     *
     * @param dto DTO.
     * @return Entidade convertida.
     */
    E toEntity(D dto);
    /**
     * Converte uma lista de DTOs do tipo {@link D} para uma lista de entidades do tipo {@link E}
     *
     * @param dtoList Lista de DTO
     * @return Lista de entidades convertidas
     */
    List<E> toEntityList(List<D> dtoList);
}
