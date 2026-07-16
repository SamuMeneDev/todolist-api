package samumene.todolist.queryfilter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import samumene.todolist.entity.Tarefa;
import samumene.todolist.entity.Usuario;

import java.time.LocalDateTime;

import static samumene.todolist.specification.TarefaSpec.*;

///
/// Filtros de busca e especificações para a busca com findAll de Tarefas.
///
@Getter @Setter
public class TarefaQueryFilter {
    /**
     * Nome da categoria da tarefa.
     */
    private String categoria;
    /**
     * Nome do status da tarefa
     */
    private String status;
    /**
     * Período inicial de busca do início de uma tarefa.
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataInicioStart;
    /**
     *  Período de termino de busca do início de uma tarefa.
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataInicioEnds;
    /**
     * Período inicial de busca do fim de uma tarefa.
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataFimStart;
    /**
     * Período de termino de busca do fim de uma tarefa.
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataFimEnds;
    /**
     * Referência do usuário autenticado, usado para buscar suas tarefas.
     */
    private Usuario usuario;
    /**
     * Metodo traz um objeto de uma só specifications com todos os filtros aplicados.
     *
     * @return o resultado do encadiamento de specifications, que é usado na query para filtrar.
     */
    public Specification<Tarefa> getSpecification() {
        return usuarioEquals(usuario)
            .and(categoriaEquals(categoria))
            .and(statusEquals(status))
            .and(dataInicioStarts(dataInicioStart))
            .and(dataInicioEnds(dataInicioEnds))
            .and(dataFimStarts(dataFimStart))
            .and(dataFimEnds(dataFimEnds));

    }
}
