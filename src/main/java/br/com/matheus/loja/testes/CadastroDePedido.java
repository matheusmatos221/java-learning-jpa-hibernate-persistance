package br.com.matheus.loja.testes;

import br.com.matheus.loja.dao.CategoriaDao;
import br.com.matheus.loja.dao.ClienteDao;
import br.com.matheus.loja.dao.PedidoDao;
import br.com.matheus.loja.dao.ProdutoDao;
import br.com.matheus.loja.modelo.*;
import br.com.matheus.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDePedido {
    public static void main(String[] args) {
        popularBancoDeDados();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        Produto produto = produtoDao.buscarPorId(1L);
        Cliente cliente = clienteDao.buscarPorId(1L);

        em.getTransaction().begin();

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));

        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);

        em.getTransaction().commit();
    }
    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULAR");
        Produto celular = new Produto("Xiaomi RedMi", "Celular muito legal", new BigDecimal("800"), celulares);
        Cliente cliente = new Cliente("Matheus", "123456");

        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
    }
}
