package samumene.todolist.service;

import org.springframework.stereotype.Service;
import samumene.todolist.dto.request.categoria.CategoriaChangeStatusRequest;
import samumene.todolist.dto.request.categoria.CategoriaEditRequest;
import samumene.todolist.dto.request.categoria.CategoriaSaveRequest;
import samumene.todolist.dto.response.CategoriaResponse;
import samumene.todolist.entity.Categoria;
import samumene.todolist.entity.Usuario;
import samumene.todolist.enumeration.StatusCategoria;
import samumene.todolist.mapper.CategoriaMapper;
import samumene.todolist.queryfilter.CategoriaQueryFilter;
import samumene.todolist.repository.CategoriaRepository;

import java.util.List;
import java.util.NoSuchElementException;


/**
 * Classe de serviço que impõe regras de negócio relacionadas à {@link Categoria}
 */
@Service
public class CategoriaService implements InnerResourceValidation<Usuario, Categoria> {
    // Dependências
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    // Injeção de dependências
    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }
    // Métodos
    /**
     * Faz validações e perssiste uma nova categoria ao usuário.
     *
     * @param request Objeto de requisição.
     * @param usuario Referência do usuário autenticado.
     */
    public void save(CategoriaSaveRequest request, Usuario usuario) {
        var categoria = new Categoria();
        categoria.setTitulo(request.titulo());
        categoria.setDescricao(request.descricao());
        categoria.setUsuario(usuario);
        categoria.setStatus(StatusCategoria.ATIVA);

        this.categoriaRepository.save(categoria);
    }
    /**
     * Busca todas as categorias de um usuário, aplicando filtragem na busca
     *
     * @param usuario Referência do usuário autenticado.
     * @param queryFilter Filtros de busca.
     * @return Retorna lista das categorias do usuario com aplicação dos filtros.
     */
    public List<CategoriaResponse> findAll(Usuario usuario, CategoriaQueryFilter queryFilter) {
        queryFilter.setUsuario(usuario);
        return this.categoriaMapper
            .toDTOList(this.categoriaRepository.findAll(queryFilter.getSpecification()));
    }
    /**
     * Muda o status de uma categoria de um usuário.
     *
     * @param idCategoria Id da categoria.
     * @param request Objeto de requisição.
     * @param usuario Referência do usuário autenticado.
     */
    public void changeStatus(Long idCategoria, CategoriaChangeStatusRequest request, Usuario usuario) {
        Categoria categoria = this.categoriaRepository.findById(idCategoria)
                .orElseThrow(()->new NoSuchElementException("Categoria não encontrada"));

        // Tentando mudar categoria de outro usuário
        this.validateInnerResource(usuario, categoria);

        categoria.setStatus(StatusCategoria.valueOf(request.status()));
        this.categoriaRepository.save(categoria);
    }
    /**
     * Edita os dados de uma categoria de um usuário.
     *
     * @param idCategoria Id da categoria.
     * @param request Objeto de requisição.
     * @param usuario Referência do usuário autenticado.
     */
    public void edit(Long idCategoria, CategoriaEditRequest request, Usuario usuario) {
        Categoria categoria = this.categoriaRepository.findByIdAndStatus(idCategoria, StatusCategoria.ATIVA)
                .orElseThrow(()->new NoSuchElementException("Categoria não encontrada"));

        // Tentando mudar tarefa de outro usuário
        this.validateInnerResource(usuario, categoria);

        categoria.setTitulo(request.titulo());
        categoria.setDescricao(request.descricao());

        this.categoriaRepository.save(categoria);
    }
    /**
     * Deleta uma categoria de um usuário.
     *
     * @param idCategoria Id da categoria.
     * @param usuario Referência do usuário autenticado.
     */
    public void deleteById(Long idCategoria, Usuario usuario) {
        Categoria categoria = this.categoriaRepository.findById(idCategoria)
            .orElseThrow(()-> new NoSuchElementException("Categoria não encontrada"));

        // Tentando deletar categoria de outros usuário
        this.validateInnerResource(usuario, categoria);

        this.categoriaRepository.deleteById(idCategoria);
    }

    // Implementações
    @Override
    public void validateInnerResource(Usuario entity, Categoria resource) {
        if(!resource.getUsuario().getId().equals(entity.getId())) {
            throw new IllegalArgumentException("Ação não permitida");
        }
    }
}
