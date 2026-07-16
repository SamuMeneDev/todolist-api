package samumene.todolist.specification;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;
import samumene.todolist.entity.Tarefa;
import samumene.todolist.entity.Usuario;
import samumene.todolist.enumeration.StatusTarefa;

import java.time.LocalDateTime;

/**
 * Métodos que filtram uma query de {@link Tarefa}
 */
public class TarefaSpec {
    /**
     * Filtra as categorias pertencentes ao usuario.
     * @param usuario Referência do usuário.
     */
    public static Specification<Tarefa> usuarioEquals(Usuario usuario) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.join("usuario").get("id"), usuario.getId()
        );
    }
    /**
     * Filtra as tarefas pelo nome da categoria.
     * @param nomeCategoria Nome da categoria.
     */
    public static Specification<Tarefa> categoriaEquals(String nomeCategoria) {
        return (root, query, criteriaBuilder) -> {
            if(ObjectUtils.isEmpty(nomeCategoria)) {
                return null;
            } else {
                return criteriaBuilder.like(root.join("categoria").get("titulo"), nomeCategoria + "%");
            }
        };
    }
    /**
     * Filtra as tarefas pelo status.
     *
     * @param status Nome do status filtrado, baseado em {@link StatusTarefa}
     */
    public static Specification<Tarefa> statusEquals(String status) {
        return (root, query, criteriaBuilder) -> {
            if(ObjectUtils.isEmpty(status)) {
                return null;
            } else {
                return criteriaBuilder.equal(root.get("status"), StatusTarefa.valueOf(status));
            }
        };
    }
    /**
     * Filtra as tarefas pela periodo da data de inicio.
     * @param dataInicio data de inicio das tarefas.
     */
    public static Specification<Tarefa> dataInicio(LocalDateTime dataInicio) {
        return (root, query, criteriaBuilder) -> {
          if(ObjectUtils.isEmpty(dataInicio)) {
              return null;
          } else {
              return criteriaBuilder.greaterThanOrEqualTo(root.get("dataInicio"), dataInicio);
          }
        };
    }
    /**
     * Filtra as tarefas pela periodo da data de término.
     * @param dataFim periodo da data de término das tarefas.
     */
    public static Specification<Tarefa> dataFim(LocalDateTime dataFim) {
        return (root, query, criteriaBuilder) -> {
            if(ObjectUtils.isEmpty(dataFim)) {
                return null;
            } else {
                return criteriaBuilder.and(criteriaBuilder.greaterThan(root.get("dataFim"), dataFim),
                    criteriaBuilder.isNotNull(root.get("dataFim")));
            }
        };
    }
}
