package samumene.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import samumene.todolist.entity.Usuario;

import java.util.Optional;

/**
 * Camada de acesso ao Banco de dados relacionado à Entidade {@link Usuario}
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    /**
     * Busca um usuário pelo seu email cadastrado.
     *
     * @param email Email do usuário.
     * @return um {@link Optional} do usuário buscado.
     */
    Optional<Usuario> findByEmail(String email);
    /**
     * Verifica se o usuário existe pelo seu email cadastrado.
     *
     * @param email Email do usuário
     * @return retorna {@code true} se existir um usuário cadastrado com o email passado.
     */
    boolean existsByEmail(String email);
}
