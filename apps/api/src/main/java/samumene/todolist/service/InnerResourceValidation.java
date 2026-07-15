package samumene.todolist.service;

/** Métodos para validar se um recurso pertence a uma entidade.
 *
 * @param <A> Representa o tipo forte do relacionamento (Ex. Usuario)
 * @param <B> Representa o recurso do relacionamento (Ex. Tarefa)
 */
public interface InnerResourceValidation<A, B> {

    void validateInnerResource(A entity, B resource);
}
