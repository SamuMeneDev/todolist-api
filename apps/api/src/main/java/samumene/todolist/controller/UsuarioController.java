package samumene.todolist.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import samumene.todolist.dto.request.usuario.UsuarioLoginRequest;
import samumene.todolist.dto.request.usuario.UsuarioRegisterRequest;
import samumene.todolist.dto.response.TokenResponse;
import samumene.todolist.dto.response.UsuarioResponse;
import samumene.todolist.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /** Busca os dados de um usuário.
     *
     * @param id Id do usuário.
     * @return Retorna o objeto do usuario com o respectivo Id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Long id) {
        UsuarioResponse usuario = this.usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    /** Endpoint para cadastro de um novo usuário.
     *
     * @param request Objeto de requisição.
     */
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid UsuarioRegisterRequest request) {
        this.usuarioService.register(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /** Endpoint de login
     *
     * @param request Objeto de requisição.
     * @return Token do usuario.
     */
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid UsuarioLoginRequest request) {
        TokenResponse token = this.usuarioService.login(request);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
