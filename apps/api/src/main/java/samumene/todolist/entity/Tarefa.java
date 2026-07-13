package samumene.todolist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import samumene.todolist.enumeration.StatusTarefa;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String descricao;

    @ManyToOne
    private Categoria categoria;
    private LocalDateTime dataInicio = LocalDateTime.now();
    private LocalDateTime dataFim;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255)")
    private StatusTarefa status;

    @JsonIgnore
    @ManyToOne
    private Usuario usuario;
}
