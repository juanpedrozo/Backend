-- CATEGORIAS

insert into categoria (nome, descricao) values ('Informática', 'Produtos de informática');
insert into categoria (nome, descricao) values ('Livros', 'Livros técnicos e educacionais');
insert into categoria (nome, descricao) values ('Games', 'Produtos relacionados a jogos');
insert into categoria (nome, descricao) values ('Periféricos', 'Acessórios e periféricos');
insert into categoria (nome, descricao) values ('Escritório', 'Produtos para escritório');

-- PRODUTOS

insert into produto (nome, descricao, preco, estoque, categoria_id) values ('Notebook Lenovo', 'Notebook para estudos e trabalho', 3500.00, 10, 1);
insert into produto (nome, descricao, preco, estoque, categoria_id) values ('Código Limpo', 'Livro do autor Robert C. Martin', 87.34, 20, 2);
insert into produto (nome, descricao, preco, estoque, categoria_id) values ('Controle Gamer', 'Controle para jogos', 299.90, 15, 3);
insert into produto (nome, descricao, preco, estoque, categoria_id) values ('Mouse Gamer', 'Mouse com alta precisão', 150.00, 30, 4);
insert into produto (nome, descricao, preco, estoque, categoria_id) values ('Cadeira de Escritório', 'Cadeira ergonômica', 899.90, 8, 5);

-- CLIENTES

insert into cliente (nome, email, telefone) values ('Ana Silva', 'ana@email.com', '14999990001');
insert into cliente (nome, email, telefone) values ('Carlos Souza', 'carlos@email.com', '14999990002');
insert into cliente (nome, email, telefone) values ('Beatriz Lima', 'beatriz@email.com', '14999990003');
insert into cliente (nome, email, telefone) values ('Joao Santos', 'joao@email.com', '14999990004');
insert into cliente (nome, email, telefone) values ('Mariana Oliveira', 'mariana@email.com', '14999990005');

-- PEDIDOS

insert into pedido (data, status, valor_total, cliente_id) values ('2026-09-01 10:00:00', 'PAGO', 3500.00, 1);
insert into pedido (data, status, valor_total, cliente_id) values ('2026-09-01 11:00:00', 'PAGO', 87.34, 2);
insert into pedido (data, status, valor_total, cliente_id) values ('2026-09-01 12:00:00', 'PENDENTE', 299.90, 3);
insert into pedido (data, status, valor_total, cliente_id) values ('2026-09-01 13:00:00', 'PAGO', 300.00, 4);
insert into pedido (data, status, valor_total, cliente_id) values ('2026-09-01 14:00:00', 'PENDENTE', 899.90, 5);

-- ITENS DOS PEDIDOS

insert into item_pedido (quantidade, valor_unitario, pedido_id, produto_id) values (1, 3500.00, 1, 1);
insert into item_pedido (quantidade, valor_unitario, pedido_id, produto_id) values (1, 87.34, 2, 2);
insert into item_pedido (quantidade, valor_unitario, pedido_id, produto_id) values (1, 299.90, 3, 3);
insert into item_pedido (quantidade, valor_unitario, pedido_id, produto_id) values (2, 150.00, 4, 4);
insert into item_pedido (quantidade, valor_unitario, pedido_id, produto_id) values (1, 899.90, 5, 5);

-- PAGAMENTOS

insert into pagamento (valor, data, status, tipo, pedido_id) values (3500.00, '2026-09-01 10:05:00', 'APROVADO', 'PIX', 1);
insert into pagamento (valor, data, status, tipo, pedido_id) values (87.34, '2026-09-01 11:05:00', 'APROVADO', 'CARTAO', 2);
insert into pagamento (valor, data, status, tipo, pedido_id) values (299.90, '2026-09-01 12:05:00', 'PENDENTE', 'BOLETO', 3);
insert into pagamento (valor, data, status, tipo, pedido_id) values (300.00, '2026-09-01 13:05:00', 'APROVADO', 'PIX', 4);
insert into pagamento (valor, data, status, tipo, pedido_id) values (899.90, '2026-09-01 14:05:00', 'PENDENTE', 'CARTAO', 5);