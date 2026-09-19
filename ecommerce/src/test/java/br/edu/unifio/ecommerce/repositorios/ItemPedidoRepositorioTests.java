package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import br.edu.unifio.ecommerce.entidades.ItemPedido;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ItemPedidoRepositorioTests {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private ItemPedidoRepositorio itemPedidoRepositorio;

    @Test
    @Order(1)
    public void deveListarTodosOsItensPedidos() {

        List<ItemPedido> itens = itemPedidoRepositorio.findAll(Sort.by("id"));

        assertEquals(5, itens.size());
        assertEquals(1, itens.get(0).getQuantidade());
        assertEquals(2, itens.get(3).getQuantidade());
    }

    @Test
    @Order(2)
    public void deveBuscarUmItemPedidoPorId() {

        ItemPedido itemPedido = itemPedidoRepositorio
                .findById(1)
                .orElseThrow();

        assertEquals(1, itemPedido.getQuantidade());
        assertEquals(new BigDecimal("3500.00"), itemPedido.getValorUnitario());
    }

    @Test
    @Order(3)
    public void deveExcluirUmItemPedidoPorId() {

        ItemPedido itemPedido = new ItemPedido();

        itemPedido.setQuantidade(1);
        itemPedido.setValorUnitario(new BigDecimal("100.00"));

        itemPedido.setPedido(
                pedidoRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        itemPedido.setProduto(
                produtoRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        itemPedidoRepositorio.save(itemPedido);

        assertTrue(
                itemPedidoRepositorio.existsById(itemPedido.getId())
        );

        itemPedidoRepositorio.deleteById(itemPedido.getId());

        assertFalse(
                itemPedidoRepositorio.existsById(itemPedido.getId())
        );
    }

    @Test
    @Order(4)
    public void deveSalvarUmItemPedido() {

        ItemPedido itemPedido = new ItemPedido();

        itemPedido.setQuantidade(2);
        itemPedido.setValorUnitario(new BigDecimal("50.00"));

        itemPedido.setPedido(
                pedidoRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        itemPedido.setProduto(
                produtoRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        itemPedidoRepositorio.save(itemPedido);

        assertTrue(
                itemPedidoRepositorio.existsById(itemPedido.getId())
        );

        assertEquals(
                2,
                itemPedidoRepositorio
                        .findById(itemPedido.getId())
                        .orElseThrow()
                        .getQuantidade()
        );
    }

    @Test
    @Order(5)
    public void deveAlterarUmItemPedido() {

        ItemPedido itemPedido = new ItemPedido();

        itemPedido.setQuantidade(1);
        itemPedido.setValorUnitario(new BigDecimal("50.00"));

        itemPedido.setPedido(
                pedidoRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        itemPedido.setProduto(
                produtoRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        itemPedidoRepositorio.save(itemPedido);

        itemPedido.setQuantidade(3);
        itemPedido.setValorUnitario(new BigDecimal("75.00"));

        itemPedidoRepositorio.save(itemPedido);

        ItemPedido itemPedidoAlterado = itemPedidoRepositorio
                .findById(itemPedido.getId())
                .orElseThrow();

        assertEquals(3, itemPedidoAlterado.getQuantidade());
        assertEquals(
                new BigDecimal("75.00"),
                itemPedidoAlterado.getValorUnitario()
        );
    }
}