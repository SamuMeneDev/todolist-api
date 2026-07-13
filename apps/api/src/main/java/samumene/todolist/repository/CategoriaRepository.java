package samumene.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import samumene.todolist.entity.Categoria;
import samumene.todolist.entity.Usuario;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByUsuario(Usuario usuario);
}
