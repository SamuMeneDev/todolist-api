package samumene.todolist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import samumene.todolist.enumeration.StatusTarefa;

import java.time.LocalDateTime;

/**
 * Entidade que representa uma tarefa de um usuário no sistema.
 */
@Getter @Setter
@NoArgsConstructor
@Entity
public class Tarefa {
    /**
     * Id único da tarefa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Título da tarefa.
     */
    @Column(nullable = false)
    private String titulo;
    /**
     * Descrição adicional da tarefa.
     */
    private String descricao;
    /**
     * Categoria dessa tarefa.
     */
    @ManyToOne
    private Categoria categoria;
    /**
     * Data de início dessa tarefa.
     * @apiNote Caso omitida, o valor será a data de instância da tarefa.
     * @see Categoria
     */
    private LocalDateTime dataInicio = LocalDateTime.now();
    /**
     * Data de termino dessa tarefa.
     */
    private LocalDateTime dataFim;
    /**
     * Status dessa tarefa
     * @apiNote Inicialmente quando criada, começa como {@code PENDENTE}
     * @see StatusTarefa
     */
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255)")
    private StatusTarefa status;
    /**
     * Usuário proprietário dessa tarefa.
     * @see Usuario
     */
    @JsonIgnore
    @ManyToOne
    private Usuario usuario;
}
