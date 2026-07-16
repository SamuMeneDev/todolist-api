package samumene.todolist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import samumene.todolist.enumeration.StatusCategoria;

import java.util.List;

/**
 * Entidade que representa a categoria de uma tarefa.
 */
@Entity
@Getter @Setter
@NoArgsConstructor
public class Categoria {
    /**
     * Id único da categoria no sistema.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Título da categoria.
     */
    @Column(nullable = false)
    private String titulo;
    /**
     * Descrição adicional da categoria.
     */
    private String descricao;
    /**
     * Status da categoria.
     * @see StatusCategoria
     */
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255)")
    private StatusCategoria status;
    /**
     * Usuário proprietario dessa categoria.
     * @apiNote O valor inicial da categoria quanto criada é {@code ATIVA}.
     * @see Usuario
     */
    @JsonIgnore
    @ManyToOne
    private Usuario usuario;
    /**
     * Lista de {@link Tarefa} que utilizam essa categoria.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    private List<Tarefa> tarefas;
}
