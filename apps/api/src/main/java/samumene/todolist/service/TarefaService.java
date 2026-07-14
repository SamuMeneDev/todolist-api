package samumene.todolist.service;

import org.springframework.stereotype.Service;
import samumene.todolist.dto.request.tarefa.TarefaSaveRequest;
import samumene.todolist.dto.response.TarefaResponse;
import samumene.todolist.entity.Tarefa;
import samumene.todolist.entity.Usuario;
import samumene.todolist.enumeration.StatusCategoria;
import samumene.todolist.enumeration.StatusTarefa;
import samumene.todolist.repository.CategoriaRepository;
import samumene.todolist.repository.TarefaRepository;

import java.util.List;
import java.util.Objects;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final CategoriaRepository categoriaRepository;

    public TarefaService(TarefaRepository tarefaRepository, CategoriaRepository categoriaRepository) {
        this.tarefaRepository = tarefaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public void save(TarefaSaveRequest request, Usuario usuario) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(request.titulo());
        tarefa.setDescricao(request.descricao());
        if(!Objects.isNull(request.dataInicio())) tarefa.setDataInicio(request.dataInicio());
        tarefa.setDataFim(request.dataFim());
        tarefa.setUsuario(usuario);

        if(!Objects.isNull(request.categoriaId())) {
            tarefa.setCategoria(this.categoriaRepository
                    .findByIdAndStatus(request.categoriaId(), StatusCategoria.ATIVA)
                    .orElseThrow(IllegalArgumentException::new)
            );
        }

        tarefa.setStatus(StatusTarefa.PENDENTE);

        this.tarefaRepository.save(tarefa);
    }

    public List<TarefaResponse> findAll(Usuario usuario) {
        return null;
    }

    public void edit(Long id, Usuario usuario) {

    }

    public void delete(Long id, Usuario usuario) {

    }

    public void toggleTarefa(Long id, Usuario usuario) {

    }

}
