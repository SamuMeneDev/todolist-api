package samumene.todolist.mapper;

/** Extende todo os metodo de conversão, de DTO para Entidade e vice-versa.
 *
 * @param <D> Tipo da DTO.
 * @param <E> TIpo da Entidade.
 */
public interface BaseMapper<D, E> extends DTOEntityParser<D, E>, EntityDTOParser<D, E> {
}
