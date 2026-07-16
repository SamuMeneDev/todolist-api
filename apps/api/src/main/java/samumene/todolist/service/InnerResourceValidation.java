package samumene.todolist.service;

/** Métodos para validar se um recurso pertence a uma entidade.
 *
 * @param <A> Representa o tipo forte do relacionamento (Ex. Usuario)
 * @param <B> Representa o recurso do relacionamento (Ex. Tarefa)
 */
public interface InnerResourceValidation<A, B> {
    /**
     * Valida se o recurso ou dependência {@link B}, pertence à entidade {@link A}
     *
     * @param entity Entidade ou objeto do lado forte do relacionamento.
     * @param resource Recurso ou objeto que é possuído pelo tipo {@link A}
     */
    void validateInnerResource(A entity, B resource);
}
