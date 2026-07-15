package samumene.todolist.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import samumene.todolist.dto.request.tarefa.TarefaEditRequest;
import samumene.todolist.dto.request.tarefa.TarefaSaveRequest;
import samumene.todolist.dto.response.TarefaResponse;
import samumene.todolist.entity.Usuario;
import samumene.todolist.service.TarefaService;

import java.util.List;

@RequestMapping("/tarefa")
@RestController
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    /** Salva uma nova tarefa para o usuário.
     *
     * @param request Objeto de requisição.
     * @param usuario Referência do usuaário autenticado.
     */
    @PostMapping("/save")
    public ResponseEntity<Void> save(
            @RequestBody @Valid TarefaSaveRequest request,
            @AuthenticationPrincipal Usuario usuario
    ) {
        this.tarefaService.save(request, usuario);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /** Lista todas as tarefa de um usuário
     *
     * @param usuario Referência do usuario autenticado.
     * @return Lista de todas as tarefas do usuario
     */
    @GetMapping("/findAll")
    public ResponseEntity<List<TarefaResponse>> findAll(
            @AuthenticationPrincipal Usuario usuario
    ) {
        var lista = this.tarefaService.findAll(usuario);
        return ResponseEntity.ok(lista);
    }

    /** Endpoint que alterna o status da tarefa entre PENDENTE e CONCLUIDA.
     *
     * @param id Id da tarefa.
     * @param usuario Referêcia do usuário autenticado.
     */
    @PatchMapping("/toggle/{id}")
    public ResponseEntity<Void> changeStatus(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuario
    ) {
        this.tarefaService.toggleTarefa(id, usuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /** Deleta uma tarefa.
     *
     * @param id Id da tarefa.
     * @param usuario Referência do usuaário autenticado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuario
    ) {
        this.tarefaService.delete(id, usuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /** Endpoint para edição das propriedades de uma tarefa.
     *
     * @param id Id da Tarefa.
     * @param request Objeto de requisição com os campos que serão editados.
     * @param usuario Referência do usuário autenticado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> edit(
            @PathVariable Long id,
            @RequestBody @Valid TarefaEditRequest request,
            @AuthenticationPrincipal Usuario usuario
    ) {
        this.tarefaService.edit(id, request, usuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
