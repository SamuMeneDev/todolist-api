package samumene.todolist.queryfilter;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import samumene.todolist.entity.Categoria;
import samumene.todolist.entity.Usuario;

import static samumene.todolist.specification.CategoriaSpec.statusEquals;
import static samumene.todolist.specification.CategoriaSpec.usuarioEquals;


/**
 *  Filtros de busca e especificações para a busca com findAll de Categorias.
 */
@Getter @Setter
public class CategoriaQueryFilter {
    /**
     * Nome do Status da categoria
     */
    private String status;
    /**
     * Referência do usuário autenticado, para busca de categorias deste usuário
     */
    private Usuario usuario;
    /**
     * Metodo traz um objeto de uma só specifications com todos os filtros aplicados.
     *
     * @return o resultado do encadiamento de specifications, que é usado na query para filtrar.
     */
    public Specification<Categoria> getSpecification() {
        return usuarioEquals(usuario)
                .and(statusEquals(status));
    }
}
