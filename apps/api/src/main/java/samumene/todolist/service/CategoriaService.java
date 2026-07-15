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

@Service
public class CategoriaService implements InnerResourceValidation<Usuario, Categoria> {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    public void save(CategoriaSaveRequest request, Usuario usuario) {

        var categoria = new Categoria();
        categoria.setTitulo(request.titulo());
        categoria.setDescricao(request.descricao());
        categoria.setUsuario(usuario);
        categoria.setStatus(StatusCategoria.ATIVA);

        this.categoriaRepository.save(categoria);
    }

    public List<CategoriaResponse> findAll(Usuario usuario, CategoriaQueryFilter queryFilter) {
        return this.categoriaMapper
            .toDTOList(this.categoriaRepository.findAllByUsuario(usuario, queryFilter.getSpecification()));
    }

    public void changeStatus(Long idCategoria, CategoriaChangeStatusRequest request, Usuario usuario) {
        Categoria categoria = this.categoriaRepository.findById(idCategoria)
                .orElseThrow(NoSuchElementException::new);

        // Tentando mudar categoria de outro usuário
        this.validateInnerResource(usuario, categoria);

        categoria.setStatus(StatusCategoria.valueOf(request.status()));

        this.categoriaRepository.save(categoria);
    }

    public void edit(Long idCategoria, CategoriaEditRequest request, Usuario usuario) {
        Categoria categoria = this.categoriaRepository.findByIdAndStatus(idCategoria, StatusCategoria.ATIVA)
                .orElseThrow(NoSuchElementException::new);

        // Tentando mudar tarefa de outro usuário
        this.validateInnerResource(usuario, categoria);

        categoria.setTitulo(request.titulo());
        categoria.setDescricao(request.descricao());

        this.categoriaRepository.save(categoria);
    }

    public void deleteById(Long id, Usuario usuario) {
        Categoria categoria = this.categoriaRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        // Tentando deletar categoria de outros usuário
        this.validateInnerResource(usuario, categoria);

        this.categoriaRepository.deleteById(id);
    }

    @Override
    public void validateInnerResource(Usuario entity, Categoria resource) {
        if(!resource.getUsuario().getId().equals(entity.getId())) {
            throw new IllegalArgumentException();
        }
    }
}
