package samumene.todolist.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import samumene.todolist.dto.request.usuario.UsuarioLoginRequest;
import samumene.todolist.dto.request.usuario.UsuarioRegisterRequest;
import samumene.todolist.dto.response.TokenResponse;
import samumene.todolist.dto.response.UsuarioResponse;
import samumene.todolist.entity.Usuario;
import samumene.todolist.mapper.UsuarioMapper;
import samumene.todolist.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Classe de serviço que opera as validações e ações de acesso
 * e autenticação dos usuário na aplicação.
 */
@Service
public class UsuarioService implements UserDetailsService {
    // Dependências
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    // Injeção de dependências
    public UsuarioService(UsuarioRepository usuarioRepository, @Lazy AuthenticationManager authenticationManager, TokenService tokenService, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }

    // Métodos
    /**
     * Busca um usuário pelo Id
     *
     * @param id Id do usuário.
     * @return Retorna o usuário encontrado pelo Id passado.
     */
    public UsuarioResponse findById(Long id) {
        Usuario usuario = this.usuarioRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        return this.usuarioMapper.toDTO(usuario);
    }
    /**
     * Faz o cadastro de um novo usuário no sistema.
     * @param request Objeto de requisição.
     */
    public void register(UsuarioRegisterRequest request) {
        // Validação de usuário ja cadastrado
        if(this.usuarioRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Um usuário com esse email ja foi cadastrado");
        }

        var usuario = new Usuario();
        usuario.setEmail(request.email());
        usuario.setNome(request.nome());
        usuario.setSenha(this.passwordEncoder.encode(request.senha()));

        this.usuarioRepository.save(usuario);
    }
    /**
     * Faz a autenticação de usuário ja cadastrado no sistema.
     *
     * @param request Objeto de requisição.
     * @return Retorna um objeto com dados do login e o token de acesso.
     */
    public TokenResponse login(UsuarioLoginRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return new TokenResponse(
                this.tokenService.generateToken((Usuario) Objects.requireNonNull(auth.getPrincipal())),
                request.email(),
                LocalDateTime.now()
        );
    }

    // Implementações
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.usuarioRepository.findByEmail(username).orElseThrow(()->new NoSuchElementException("Usuário não encontrado"));
    }
}
