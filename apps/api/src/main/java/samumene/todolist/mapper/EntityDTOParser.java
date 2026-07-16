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
    /**
     * Converte uma Entidade do tipo {@link E} para uma DTO do tipo {@link D}
     *
     * @param entity Entidade.
     * @return DTO convertida.
     */
    D toDTO(E entity);
    /**
     * Converte uma lista de Entidades do tipo {@link E} para uma lista de DTOs do tipo {@link D}
     *
     * @param entityList Lista de Entidades.
     * @return Lista de DTOs convertidas
     */
    List<D> toDTOList(List<E> entityList);
}
