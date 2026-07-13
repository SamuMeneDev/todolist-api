package samumene.todolist.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import samumene.todolist.dto.request.CategoriaSaveRequest;
import samumene.todolist.dto.response.CategoriaResponse;
import samumene.todolist.entity.Usuario;
import samumene.todolist.service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    /** Cria uma nova categoria de tarefas.
     *
     * @param request Objeto de requisição
     * @param usuario Referência do usuario autenticado
     */
    @PostMapping("/save")
    public ResponseEntity<Void> save(
            @RequestBody @Valid CategoriaSaveRequest request,
            @AuthenticationPrincipal Usuario usuario
    ){
        this.categoriaService.save(request, usuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /** Busca todas as categorias do usuário.
     *
     * @param usuario Referência do usuário autenticado.
     * @return Lista de Categorias.
     */
    @GetMapping("/findAll")
    public ResponseEntity<List<CategoriaResponse>> findAll(@AuthenticationPrincipal Usuario usuario) {
        var lista = this.categoriaService.findAll(usuario);
        return ResponseEntity.ok(lista);
    }
}
