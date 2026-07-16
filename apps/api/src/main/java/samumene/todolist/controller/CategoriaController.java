package samumene.todolist.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import samumene.todolist.config.SecurityConfig;
import samumene.todolist.dto.request.categoria.CategoriaChangeStatusRequest;
import samumene.todolist.dto.request.categoria.CategoriaEditRequest;
import samumene.todolist.dto.request.categoria.CategoriaSaveRequest;
import samumene.todolist.dto.response.CategoriaResponse;
import samumene.todolist.entity.Usuario;
import samumene.todolist.queryfilter.CategoriaQueryFilter;
import samumene.todolist.service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@SecurityRequirement(name = SecurityConfig.SECURITY)
@Tag(name = "Categoria Controller", description = "Controller das categorias das tarefas")
public class CategoriaController {

    // Dependências
    private final CategoriaService categoriaService;

    // Injeção de dependência
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
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /** Busca todas as categorias do usuário.
     *
     * @param usuario Referência do usuário autenticado.
     * @return Lista de Categorias.
     */
    @GetMapping("/findAll")
    public ResponseEntity<List<CategoriaResponse>> findAll(
            @AuthenticationPrincipal Usuario usuario,
            CategoriaQueryFilter queryFilter
    ) {
        var lista = this.categoriaService.findAll(usuario, queryFilter);
        return ResponseEntity.ok(lista);
    }


    /** Endpoint que muda o status da categoria.
     *
     * @param id Id da categoria.
     * @param request Objeto da requisição.
     * @param usuario Referência do usuário autenticado.
     */
    @PatchMapping("/status/{id}")
    public ResponseEntity<Void> changeStatus(
            @PathVariable Long id,
            @RequestBody @Valid CategoriaChangeStatusRequest request,
            @AuthenticationPrincipal Usuario usuario
    ) {
        this.categoriaService.changeStatus(id, request, usuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /** Edita os dados de uma categoria de um usuário
     *
     * @param id Id da categoria.
     * @param request Objeto de requisição com as ropriedades editadas.
     * @param usuario Referência do usuário autenticado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> edit(
            @PathVariable Long id,
            @RequestBody @Valid CategoriaEditRequest request,
            @AuthenticationPrincipal Usuario usuario
    ) {
        this.categoriaService.edit(id, request, usuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /** Deleta uma categoria.
     *
     * @param id Id da categoria.
     * @param usuario Referência do usuário autenticado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
        @PathVariable  Long id,
        @AuthenticationPrincipal Usuario usuario
    ) {
        this.categoriaService.deleteById(id, usuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
