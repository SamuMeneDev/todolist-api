package samumene.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import samumene.todolist.entity.Tarefa;
import samumene.todolist.entity.Usuario;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findAllByUsuario(Usuario usuario);
}
