package samumene.todolist.specification;

import org.springframework.data.jpa.domain.Specification;
import samumene.todolist.entity.Categoria;
import samumene.todolist.entity.Usuario;
import samumene.todolist.enumeration.StatusCategoria;
import java.util.Objects;

/**
 * Métodos que filtram uma query de {@link Categoria}
 */
public class CategoriaSpec {
    /**
     * Filtra as categorias pertencentes ao usuario.
     * @param usuario Referência do usuário.
     */
    public static Specification<Categoria> usuarioEquals(Usuario usuario) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.join("usuario").get("id"), usuario.getId()
        );
    }
    /**
     * Filtra as categorias pelo nome do status.
     *
     * @param status Nome do status filtrado, baseado em {@link StatusCategoria}
     */
    public static Specification<Categoria> statusEquals(String status) {
        return (root, query, criteriaBuilder) -> {
            if(Objects.isNull(status)) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("status"), StatusCategoria.valueOf(status));
            }
        };
    }
}
