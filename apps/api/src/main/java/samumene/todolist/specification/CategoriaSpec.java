package samumene.todolist.specification;

import org.springframework.data.jpa.domain.Specification;
import samumene.todolist.entity.Categoria;
import samumene.todolist.enumeration.StatusCategoria;

import javax.swing.*;
import java.util.Objects;

public class CategoriaSpec {
    public static Specification<Categoria> statusEquals(String status) {
        return (root, query, criteriaBuilder) -> {
            if(Objects.isNull(status)) {
                return criteriaBuilder.equal(root.get("status"), StatusCategoria.ATIVA);
            } else {
                return criteriaBuilder.equal(root.get("status"), StatusCategoria.valueOf(status));
            }
        };
    }
}
