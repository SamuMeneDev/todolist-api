package samumene.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import samumene.todolist.entity.Tarefa;

/**
 * Camada de acesso ao Banco de dados relacionado à Entidade {@link Tarefa}
 */
public interface TarefaRepository extends JpaRepository<Tarefa, Long>, JpaSpecificationExecutor<Tarefa> {}
