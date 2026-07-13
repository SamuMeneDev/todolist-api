package samumene.todolist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import samumene.todolist.enumeration.StatusCategoria;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255)")
    private StatusCategoria status;

    @JsonIgnore
    @ManyToOne
    private Usuario usuario;


    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    private List<Tarefa> tarefas;

}
