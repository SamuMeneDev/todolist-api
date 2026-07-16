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
import samumene.todolist.queryfilter.TarefaQueryFilter;
import samumene.todolist.repository.CategoriaRepository;
import samumene.todolist.repository.TarefaRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;


/**
 * Classe de serviço que impõe regras de negócio relacionadas à {@link Tarefa}
 */
@Service
public class TarefaService implements InnerResourceValidation<Usuario, Tarefa> {
    // Dependências
    private final TarefaRepository tarefaRepository;
    private final CategoriaRepository categoriaRepository;
    private final TarefaMapper tarefaMapper;

    // Injeção de depencências
    public TarefaService(TarefaRepository tarefaRepository, CategoriaRepository categoriaRepository, TarefaMapper tarefaMapper) {
        this.tarefaRepository = tarefaRepository;
        this.categoriaRepository = categoriaRepository;
        this.tarefaMapper = tarefaMapper;
    }
    // Métodos
    /**
     * Faz validações e perssiste uma nova tarefa ao usuário.
     *
     * @param request Objeto de requisição.
     * @param usuario Referência do usuário autenticado.
     */
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
                    .orElseThrow(()->new NoSuchElementException("Categoria não encontrada"))
            );
        }

        tarefa.setStatus(StatusTarefa.PENDENTE);
        this.tarefaRepository.save(tarefa);
    }
    /**
     * Busca todas as tarefas de um usuário, aplicando filtragem na busca
     *
     * @param usuario Referência do usuário autenticado.
     * @param queryFilter Filtros de busca.
     * @return Retorna lista das tarefas do usuario com aplicação dos filtros.
     */
    public List<TarefaResponse> findAll(Usuario usuario, TarefaQueryFilter queryFilter) {
        // Passando o usuario para a specification
        queryFilter.setUsuario(usuario);
        return this.tarefaMapper.toDTOList(this.tarefaRepository.findAll(queryFilter.getSpecification()));
    }
    /**
     * Troca o status de uma tarefa entre pendente e concluída
     *
     * @param idTarefa Id da tarefa.
     * @param usuario Referência do usuário autenticado.
     */
    public void toggleTarefa(Long idTarefa, Usuario usuario) {
        Tarefa tarefa = this.tarefaRepository.findById(idTarefa)
                .orElseThrow(()->new NoSuchElementException("Tarefa não encontrada"));

        this.validateInnerResource(usuario, tarefa);

        tarefa.setStatus(
                tarefa.getStatus().equals(StatusTarefa.PENDENTE)
                ? StatusTarefa.CONCLUIDA
                : StatusTarefa.PENDENTE
        );

        this.tarefaRepository.save(tarefa);
    }
    /**
     * Edita os dados de uma tarefa de um usuário.
     *
     * @param idTarefa Id da tarefa.
     * @param request Objeto de requisição.
     * @param usuario Referência do usuário autenticado.
     */
    public void edit(Long idTarefa, TarefaEditRequest request, Usuario usuario) {

        Tarefa tarefa = this.tarefaRepository.findById(idTarefa)
                .orElseThrow(()->new NoSuchElementException("Tarefa não encontrada"));

        this.validateInnerResource(usuario, tarefa);

        tarefa.setTitulo(request.titulo());
        tarefa.setDescricao(request.descricao());
        tarefa.setDataInicio(request.dataInicio());
        tarefa.setDataFim(request.dataFim());

        if(!Objects.isNull(request.categoriaId())) {
            tarefa.setCategoria(this.categoriaRepository
                    .findByIdAndStatus(request.categoriaId(), StatusCategoria.ATIVA)
                    .orElseThrow(()->new NoSuchElementException("Categoria não encontrada"))
            );
        }
        this.tarefaRepository.save(tarefa);
    }
    /**
     * Deleta uma tarefa de um usuário.
     *
     * @param idTarefa Id da tarefa.
     * @param usuario Referência do usuário autenticado.
     */
    public void delete(Long idTarefa, Usuario usuario) {
        Tarefa tarefa = this.tarefaRepository.findById(idTarefa)
                .orElseThrow(()->new NoSuchElementException("Tarefa não encontrada"));

        validateInnerResource(usuario, tarefa);

        this.tarefaRepository.deleteById(idTarefa);
    }

    // Implemetações
    @Override
    public void validateInnerResource(Usuario entity, Tarefa resource) {
        // Tentando trocar tarefa de outro usuário
        if(!resource.getUsuario().getId().equals(entity.getId())) {
            throw new IllegalArgumentException("Não permitido");
        }
    }
}
