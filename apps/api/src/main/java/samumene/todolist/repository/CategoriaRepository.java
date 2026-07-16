package samumene.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import samumene.todolist.entity.Categoria;
import samumene.todolist.enumeration.StatusCategoria;
import java.util.Optional;

/**
 * Camada de acesso ao Banco de dados relacionado à Entidade {@link Categoria}
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long>, JpaSpecificationExecutor<Categoria> {
    /** Busca uma categoria, filtrada por seu Id e status da categoria.
     *
     * @param id Id da categoria.
     * @param status Status da categoria.
     * @return um {@link  Optional} da categoria buscada
     */
    Optional<Categoria> findByIdAndStatus(Long id, StatusCategoria status);
}
