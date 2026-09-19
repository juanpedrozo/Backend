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

import br.edu.unifio.ecommerce.entidades.Pagamento;
import br.edu.unifio.ecommerce.entidades.Pedido;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PagamentoRepositorioTests {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private PagamentoRepositorio pagamentoRepositorio;

    @Test
    @Order(1)
    public void deveListarTodosOsPagamentos() {

        List<Pagamento> pagamentos = pagamentoRepositorio.findAll(Sort.by("data"));

        assertEquals(5, pagamentos.size());
        assertEquals("APROVADO", pagamentos.get(0).getStatus());
        assertEquals("CARTAO", pagamentos.get(4).getTipo());
    }

    @Test
    @Order(2)
    public void deveBuscarUmPagamentoPorId() {

        Pagamento pagamento = pagamentoRepositorio
                .findById(1)
                .orElseThrow();

        assertEquals("APROVADO", pagamento.getStatus());
        assertEquals("PIX", pagamento.getTipo());
    }

    @Test
    @Order(3)
    public void deveExcluirUmPagamentoPorId() {

        Pedido pedido = new Pedido();

        pedido.setData(LocalDateTime.of(2026, 9, 16, 20, 0));
        pedido.setStatus("PAGO");
        pedido.setValorTotal(new BigDecimal("100.00"));

        pedido.setCliente(
                clienteRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        pedidoRepositorio.save(pedido);

        Pagamento pagamento = new Pagamento();

        pagamento.setValor(new BigDecimal("100.00"));
        pagamento.setData(LocalDateTime.of(2026, 9, 16, 20, 5));
        pagamento.setStatus("APROVADO");
        pagamento.setTipo("PIX");
        pagamento.setPedido(pedido);

        pagamentoRepositorio.save(pagamento);

        assertTrue(
                pagamentoRepositorio.existsById(pagamento.getId())
        );

        pagamentoRepositorio.deleteById(pagamento.getId());

        assertFalse(
                pagamentoRepositorio.existsById(pagamento.getId())
        );

        pedidoRepositorio.deleteById(pedido.getId());
    }

    @Test
    @Order(4)
    public void deveSalvarUmPagamento() {

        Pedido pedido = new Pedido();

        pedido.setData(LocalDateTime.of(2026, 9, 16, 21, 0));
        pedido.setStatus("PAGO");
        pedido.setValorTotal(new BigDecimal("200.00"));

        pedido.setCliente(
                clienteRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        pedidoRepositorio.save(pedido);

        Pagamento pagamento = new Pagamento();

        pagamento.setValor(new BigDecimal("200.00"));
        pagamento.setData(LocalDateTime.of(2026, 9, 16, 21, 5));
        pagamento.setStatus("APROVADO");
        pagamento.setTipo("PIX");
        pagamento.setPedido(pedido);

        pagamentoRepositorio.save(pagamento);

        assertTrue(
                pagamentoRepositorio.existsById(pagamento.getId())
        );

        assertEquals(
                "PIX",
                pagamentoRepositorio
                        .findById(pagamento.getId())
                        .orElseThrow()
                        .getTipo()
        );

        pagamentoRepositorio.deleteById(pagamento.getId());
        pedidoRepositorio.deleteById(pedido.getId());
    }

    @Test
    @Order(5)
    public void deveAlterarUmPagamento() {

        Pedido pedido = new Pedido();

        pedido.setData(LocalDateTime.of(2026, 9, 16, 22, 0));
        pedido.setStatus("PENDENTE");
        pedido.setValorTotal(new BigDecimal("300.00"));

        pedido.setCliente(
                clienteRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        pedidoRepositorio.save(pedido);

        Pagamento pagamento = new Pagamento();

        pagamento.setValor(new BigDecimal("300.00"));
        pagamento.setData(LocalDateTime.of(2026, 9, 16, 22, 5));
        pagamento.setStatus("PENDENTE");
        pagamento.setTipo("BOLETO");
        pagamento.setPedido(pedido);

        pagamentoRepositorio.save(pagamento);

        pagamento.setStatus("APROVADO");
        pagamento.setTipo("PIX");

        pagamentoRepositorio.save(pagamento);

        Pagamento pagamentoAlterado = pagamentoRepositorio
                .findById(pagamento.getId())
                .orElseThrow();

        assertEquals("APROVADO", pagamentoAlterado.getStatus());
        assertEquals("PIX", pagamentoAlterado.getTipo());

        pagamentoRepositorio.deleteById(pagamento.getId());
        pedidoRepositorio.deleteById(pedido.getId());
    }
}