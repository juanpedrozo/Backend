package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.ItemPedido;

@SpringBootTest
public class ItemPedidoRepositorioTests {

    @Autowired
    private ItemPedidoRepositorio itemPedidoRepositorio;

    @Test
    public void deveBuscarUmItemPedidoPorId() {

        ItemPedido itemPedido = itemPedidoRepositorio
                .findById(1)
                .orElseThrow();

        assertEquals(1, itemPedido.getQuantidade());
    }
}