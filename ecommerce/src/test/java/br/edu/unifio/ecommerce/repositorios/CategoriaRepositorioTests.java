package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import br.edu.unifio.ecommerce.entidades.Categoria;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoriaRepositorioTests {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Test
    @Order(1)
    public void deveListarTodasAsCategorias() {

        List<Categoria> categorias = categoriaRepositorio.findAll(Sort.by("nome"));

        assertEquals(5, categorias.size());
        assertEquals("Escritório", categorias.get(0).getNome());
        assertEquals("Periféricos", categorias.get(4).getNome());
    }

    @Test
    @Order(2)
    public void deveBuscarUmaCategoriaPorId() {

        Categoria categoria = categoriaRepositorio
                .findById(Short.parseShort("1"))
                .orElseThrow();

        assertEquals("Informática", categoria.getNome());
        assertEquals("Produtos de informática", categoria.getDescricao());
    }

    @Test
    @Order(3)
    public void deveExcluirUmaCategoriaPorId() {

        Categoria categoria = new Categoria();

        categoria.setNome("Categoria Exclusão");
        categoria.setDescricao("Descrição Exclusão");

        categoriaRepositorio.save(categoria);

        assertTrue(categoriaRepositorio.existsById(categoria.getId()));

        categoriaRepositorio.deleteById(categoria.getId());

        assertFalse(categoriaRepositorio.existsById(categoria.getId()));
    }

    @Test
    @Order(4)
    public void deveSalvarUmaCategoria() {

        Categoria categoria = new Categoria();

        categoria.setNome("Categoria Teste");
        categoria.setDescricao("Descrição Teste");

        categoriaRepositorio.save(categoria);

        assertTrue(categoriaRepositorio.existsById(categoria.getId()));

        assertEquals(
                "Categoria Teste",
                categoriaRepositorio
                        .findById(categoria.getId())
                        .orElseThrow()
                        .getNome()
        );
    }

    @Test
    @Order(5)
    public void deveAlterarUmaCategoria() {

        Categoria categoria = new Categoria();

        categoria.setNome("Categoria Original");
        categoria.setDescricao("Descrição Original");

        categoriaRepositorio.save(categoria);

        categoria.setNome("Categoria Alterada");
        categoria.setDescricao("Descrição Alterada");

        categoriaRepositorio.save(categoria);

        Categoria categoriaAlterada = categoriaRepositorio
                .findById(categoria.getId())
                .orElseThrow();

        assertEquals("Categoria Alterada", categoriaAlterada.getNome());
        assertEquals("Descrição Alterada", categoriaAlterada.getDescricao());
    }
}