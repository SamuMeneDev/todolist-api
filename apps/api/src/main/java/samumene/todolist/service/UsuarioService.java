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

@Service
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, @Lazy AuthenticationManager authenticationManager, TokenService tokenService, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioResponse findById(Long id) {
        Usuario usuario = this.usuarioRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        return this.usuarioMapper.toDTO(usuario);
    }

    public void register(UsuarioRegisterRequest request) {
        if(this.usuarioRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException();
        }
        var usuario = new Usuario();
        usuario.setEmail(request.email());
        usuario.setNome(request.nome());
        usuario.setSenha(this.passwordEncoder.encode(request.senha()));

        this.usuarioRepository.save(usuario);
    }

    public TokenResponse login(UsuarioLoginRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return new TokenResponse(
                this.tokenService.generateToken((Usuario) Objects.requireNonNull(auth.getPrincipal())),
                request.email(),
                LocalDateTime.now()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.usuarioRepository.findByEmail(username).orElseThrow(NoSuchElementException::new);
    }
}
