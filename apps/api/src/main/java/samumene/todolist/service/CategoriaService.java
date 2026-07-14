package samumene.todolist.service;

import org.springframework.stereotype.Service;
import samumene.todolist.dto.request.CategoriaChangeStatusRequest;
import samumene.todolist.dto.request.CategoriaEditRequest;
import samumene.todolist.dto.request.CategoriaSaveRequest;
import samumene.todolist.dto.response.CategoriaResponse;
import samumene.todolist.entity.Categoria;
import samumene.todolist.entity.Usuario;
import samumene.todolist.enumeration.StatusCategoria;
import samumene.todolist.mapper.CategoriaMapper;
import samumene.todolist.queryfilter.CategoriaQueryFilter;
import samumene.todolist.repository.CategoriaRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    public void save(CategoriaSaveRequest request, Usuario usuario) {
        // Se não estiver autenticado
        if(Objects.isNull(usuario)) {
            throw new IllegalArgumentException("Não autorizado");
        }

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

        // Se não estiver autenticado
        if(Objects.isNull(usuario)) {
            throw new IllegalArgumentException("Não autorizado");
        }

        // Tentando mudar categoria de outro usuário
        if(!categoria.getUsuario().getId().equals(usuario.getId())) {
            throw new IllegalArgumentException();
        }

        categoria.setStatus(StatusCategoria.valueOf(request.status()));

        this.categoriaRepository.save(categoria);
    }

    public void edit(Long idCategoria, CategoriaEditRequest request, Usuario usuario) {
        Categoria categoria = this.categoriaRepository.findByIdAndStatus(idCategoria, StatusCategoria.ATIVA)
                .orElseThrow(NoSuchElementException::new);
        // Tentando mudar tarefa de outro usuário
        if(!categoria.getUsuario().getId().equals(usuario.getId())) {
            throw new IllegalArgumentException();
        }

        categoria.setTitulo(request.titulo());
        categoria.setDescricao(request.descricao());

        this.categoriaRepository.save(categoria);
    }

    public void deleteById(Long id, Usuario usuario) {
        // Se não estiver autenticado
        if(Objects.isNull(usuario)) {
            throw new IllegalArgumentException("Não autorizado");
        }

        Categoria categoria = this.categoriaRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        // Tentando deletar categoria de outros usuário
        if(!categoria.getUsuario().getId().equals(usuario.getId())) {
            throw new IllegalArgumentException();
        }

        this.categoriaRepository.deleteById(id);
    }
}
