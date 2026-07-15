package samumene.todolist.service;

import org.springframework.stereotype.Service;
import samumene.todolist.dto.request.tarefa.TarefaEditRequest;
import samumene.todolist.dto.request.tarefa.TarefaSaveRequest;
import samumene.todolist.dto.response.TarefaResponse;
import samumene.todolist.entity.Tarefa;
import samumene.todolist.entity.Usuario;
import samumene.todolist.enumeration.StatusCategoria;
import samumene.todolist.enumeration.StatusTarefa;
import samumene.todolist.mapper.TarefaMapper;
import samumene.todolist.repository.CategoriaRepository;
import samumene.todolist.repository.TarefaRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class TarefaService implements InnerResourceValidation<Usuario, Tarefa> {

    private final TarefaRepository tarefaRepository;
    private final CategoriaRepository categoriaRepository;
    private final TarefaMapper tarefaMapper;

    public TarefaService(TarefaRepository tarefaRepository, CategoriaRepository categoriaRepository, TarefaMapper tarefaMapper) {
        this.tarefaRepository = tarefaRepository;
        this.categoriaRepository = categoriaRepository;
        this.tarefaMapper = tarefaMapper;
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
        return this.tarefaMapper.toDTOList(this.tarefaRepository.findAllByUsuario(usuario));
    }

    public void toggleTarefa(Long idTarefa, Usuario usuario) {
        Tarefa tarefa = this.tarefaRepository.findById(idTarefa)
                .orElseThrow(NoSuchElementException::new);

        this.validateInnerResource(usuario, tarefa);

        tarefa.setStatus(tarefa.getStatus().equals(StatusTarefa.PENDENTE)?StatusTarefa.CONCLUIDA:StatusTarefa.PENDENTE);

        this.tarefaRepository.save(tarefa);
    }

    public void edit(Long idTarefa, TarefaEditRequest request, Usuario usuario) {

        Tarefa tarefa = this.tarefaRepository.findById(idTarefa)
                .orElseThrow(NoSuchElementException::new);

        this.validateInnerResource(usuario, tarefa);

        tarefa.setTitulo(request.titulo());
        tarefa.setDescricao(request.descricao());
        tarefa.setDataInicio(request.dataInicio());
        tarefa.setDataFim(request.dataFim());

        if(!Objects.isNull(request.categoriaId())) {
            tarefa.setCategoria(this.categoriaRepository
                    .findByIdAndStatus(request.categoriaId(), StatusCategoria.ATIVA)
                    .orElseThrow(IllegalArgumentException::new)
            );
        }
        this.tarefaRepository.save(tarefa);
    }

    public void delete(Long idTarefa, Usuario usuario) {
        Tarefa tarefa = this.tarefaRepository.findById(idTarefa)
                .orElseThrow(NoSuchElementException::new);

        validateInnerResource(usuario, tarefa);

        this.tarefaRepository.deleteById(idTarefa);
    }


    @Override
    public void validateInnerResource(Usuario entity, Tarefa resource) {
        // Tentando trocar tarefa de outro usuário
        if(!resource.getUsuario().getId().equals(entity.getId())) {
            throw new IllegalArgumentException();
        }
    }
}
