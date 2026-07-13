package samumene.todolist.service;

import org.springframework.stereotype.Service;
import samumene.todolist.dto.request.CategoriaSaveRequest;
import samumene.todolist.dto.response.CategoriaResponse;
import samumene.todolist.entity.Categoria;
import samumene.todolist.entity.Usuario;
import samumene.todolist.enumeration.StatusCategoria;
import samumene.todolist.mapper.CategoriaMapper;
import samumene.todolist.repository.CategoriaRepository;

import java.util.List;
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

    public List<CategoriaResponse> findAll(Usuario usuario) {
        return this.categoriaMapper
                .toDTOList(this.categoriaRepository.findAllByUsuario(usuario));
    }
}
