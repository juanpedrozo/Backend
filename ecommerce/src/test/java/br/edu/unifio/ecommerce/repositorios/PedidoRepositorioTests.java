package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import br.edu.unifio.ecommerce.entidades.Pedido;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PedidoRepositorioTests {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Test
    @Order(1)
    public void deveListarTodosOsPedidos() {

        List<Pedido> pedidos = pedidoRepositorio.findAll(Sort.by("data"));

        assertEquals(5, pedidos.size());
        assertEquals("PAGO", pedidos.get(0).getStatus());
        assertEquals("PENDENTE", pedidos.get(4).getStatus());
    }

    @Test
    @Order(2)
    public void deveBuscarUmPedidoPorId() {

        Pedido pedido = new Pedido();

        pedido.setData(LocalDateTime.of(2026, 9, 16, 20, 0));
        pedido.setStatus("PAGO");
        pedido.setValorTotal(new BigDecimal("500.00"));

        pedido.setCliente(
                clienteRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        pedidoRepositorio.save(pedido);

        Pedido pedidoEncontrado = pedidoRepositorio
                .findById(pedido.getId())
                .orElseThrow();

        assertEquals("PAGO", pedidoEncontrado.getStatus());
        assertEquals(new BigDecimal("500.00"), pedidoEncontrado.getValorTotal());
    }

    @Test
    @Order(3)
    public void deveExcluirUmPedidoPorId() {

        Pedido pedido = new Pedido();

        pedido.setData(LocalDateTime.of(2026, 9, 16, 21, 0));
        pedido.setStatus("PENDENTE");
        pedido.setValorTotal(new BigDecimal("200.00"));

        pedido.setCliente(
                clienteRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        pedidoRepositorio.save(pedido);

        assertTrue(
                pedidoRepositorio.existsById(pedido.getId())
        );

        pedidoRepositorio.deleteById(pedido.getId());

        assertFalse(
                pedidoRepositorio.existsById(pedido.getId())
        );
    }

    @Test
    @Order(4)
    public void deveSalvarUmPedido() {

        Pedido pedido = new Pedido();

        pedido.setData(LocalDateTime.of(2026, 9, 16, 22, 0));
        pedido.setStatus("PAGO");
        pedido.setValorTotal(new BigDecimal("300.00"));

        pedido.setCliente(
                clienteRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        pedidoRepositorio.save(pedido);

        assertTrue(
                pedidoRepositorio.existsById(pedido.getId())
        );

        assertEquals(
                "PAGO",
                pedidoRepositorio
                        .findById(pedido.getId())
                        .orElseThrow()
                        .getStatus()
        );
    }

    @Test
    @Order(5)
    public void deveAlterarUmPedido() {

        Pedido pedido = new Pedido();

        pedido.setData(LocalDateTime.of(2026, 9, 16, 23, 0));
        pedido.setStatus("PENDENTE");
        pedido.setValorTotal(new BigDecimal("100.00"));

        pedido.setCliente(
                clienteRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        pedidoRepositorio.save(pedido);

        pedido.setStatus("PAGO");
        pedido.setValorTotal(new BigDecimal("150.00"));

        pedidoRepositorio.save(pedido);

        Pedido pedidoAlterado = pedidoRepositorio
                .findById(pedido.getId())
                .orElseThrow();

        assertEquals("PAGO", pedidoAlterado.getStatus());
        assertEquals(new BigDecimal("150.00"), pedidoAlterado.getValorTotal());
    }
}