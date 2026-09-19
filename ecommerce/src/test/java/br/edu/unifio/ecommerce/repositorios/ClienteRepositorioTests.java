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

import br.edu.unifio.ecommerce.entidades.Cliente;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteRepositorioTests {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Test
    @Order(1)
    public void deveListarTodosOsClientes() {

        List<Cliente> clientes = clienteRepositorio.findAll(Sort.by("nome"));

        assertEquals(5, clientes.size());
        assertEquals("Ana Silva", clientes.get(0).getNome());
        assertEquals("Mariana Oliveira", clientes.get(4).getNome());
    }

    @Test
    @Order(2)
    public void deveBuscarUmClientePorId() {

        Cliente cliente = clienteRepositorio
                .findById(1)
                .orElseThrow();

        assertEquals("Ana Silva", cliente.getNome());
        assertEquals("ana@email.com", cliente.getEmail());
    }

    @Test
    @Order(3)
    public void deveExcluirUmClientePorId() {

        Cliente cliente = new Cliente();

        cliente.setNome("Cliente Exclusão");
        cliente.setEmail("exclusao@email.com");
        cliente.setTelefone("14999999991");

        clienteRepositorio.save(cliente);

        assertTrue(clienteRepositorio.existsById(cliente.getId()));

        clienteRepositorio.deleteById(cliente.getId());

        assertFalse(clienteRepositorio.existsById(cliente.getId()));
    }

    @Test
    @Order(4)
    public void deveSalvarUmCliente() {

        Cliente cliente = new Cliente();

        cliente.setNome("Cliente Teste");
        cliente.setEmail("cliente.teste@email.com");
        cliente.setTelefone("14999999992");

        clienteRepositorio.save(cliente);

        assertTrue(clienteRepositorio.existsById(cliente.getId()));

        assertEquals(
                "Cliente Teste",
                clienteRepositorio
                        .findById(cliente.getId())
                        .orElseThrow()
                        .getNome()
        );
    }

    @Test
    @Order(5)
    public void deveAlterarUmCliente() {

        Cliente cliente = new Cliente();

        cliente.setNome("Cliente Original");
        cliente.setEmail("original@email.com");
        cliente.setTelefone("14999999993");

        clienteRepositorio.save(cliente);

        cliente.setNome("Cliente Alterado");
        cliente.setEmail("alterado@email.com");

        clienteRepositorio.save(cliente);

        Cliente clienteAlterado = clienteRepositorio
                .findById(cliente.getId())
                .orElseThrow();

        assertEquals("Cliente Alterado", clienteAlterado.getNome());
        assertEquals("alterado@email.com", clienteAlterado.getEmail());
    }
}