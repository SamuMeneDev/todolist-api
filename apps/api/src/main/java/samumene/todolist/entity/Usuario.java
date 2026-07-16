package samumene.todolist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import samumene.todolist.enumeration.UsuarioRoles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Entidade que representa um usuário utilizador do sistema.
 */
@Entity
@Getter @Setter
@NoArgsConstructor
public class Usuario implements UserDetails {
    /**
     * Id único do usuário no sistema.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Nome do usuário.
     */
    @Column(nullable = false)
    private String nome;
    /**
     * Email do usuário, usado para autênticação.
     */
    @Column(nullable = false)
    private String email;
    /**
     * Senha do usuário.
     */
    @Column(nullable = false)
    private String senha;
    /**
     * Lista com as tarefas criadas pelo usuário no sistema.
     * @see Tarefa
     */
    @OneToMany(mappedBy = "usuario")
    private List<Tarefa> tarefas = new ArrayList<>();
    /**
     * Lista com as categorias criadas pelo usuário no sistema.
     * @see Categoria
     */
    @OneToMany(mappedBy = "usuario")
    private List<Categoria> categorias = new ArrayList<>();


    // Implementações
    /**
     * Busca as autoridades que esse tipo de usuário possui no sistema
     * @return Coleção com as autoridades.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(UsuarioRoles.USUARIO.name()));
    }
    @Override
    public @Nullable String getPassword() {
        return this.getSenha();
    }
    @Override
    public String getUsername() {
        return this.getEmail();
    }
}
