package samumene.todolist.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import samumene.todolist.dto.request.UsuarioLoginRequest;
import samumene.todolist.dto.request.UsuarioRegisterRequest;
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

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Long id) {
        UsuarioResponse usuario = this.usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid UsuarioRegisterRequest request) {
        this.usuarioService.register(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid UsuarioLoginRequest request) {
        TokenResponse token = this.usuarioService.login(request);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
