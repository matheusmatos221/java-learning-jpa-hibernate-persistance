package br.com.matheus.loja.testes;

import br.com.matheus.loja.dao.CategoriaDao;
import br.com.matheus.loja.dao.ClienteDao;
import br.com.matheus.loja.dao.PedidoDao;
import br.com.matheus.loja.dao.ProdutoDao;
import br.com.matheus.loja.modelo.*;
import br.com.matheus.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PerformanceConsultas {
    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao pedidoDao = new PedidoDao(em);
        Pedido pedido = pedidoDao.buscarPedidoComCliente(1L);
        em.close();
        System.out.println(pedido.getCliente().getNome());

        // Precisa do JOIN FETCH para trazer as informações após o fechamento da EntityManager (EM)
//        Pedido pedido = em.find(Pedido.class, 1L);
//        em.close();
//        System.out.println(pedido.getItens().size());
//        System.out.println(pedido.getCliente().getNome());
//        System.out.println(pedido.getDate());

    }

    private static void popularBancoDeDados() {
        // Cria novas instancias
        // Instancia novas categorias
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        // Intancia novos produtos
        Produto produtoCelular = new Produto("Xiaomi RedMi", "Celular muito legal", new BigDecimal("1000"), celulares);
        Produto produtoVideogame = new Produto("PS5", "PlayStation 5", new BigDecimal("2000"), videogames);
        Produto produtoMacbook = new Produto("Macbook", "Macbook pro ", new BigDecimal("4000"), informatica);

        // Intancia novos clientes
        Cliente clienteMatheus = new Cliente("Matheus", "000001");
        Cliente clienteMarcelo = new Cliente("Marcelo", "000002");
        Cliente clienteRodrigo = new Cliente("Rodrigo", "000003");

        // Intancia novos pedidos
        //
        Pedido pedido = new Pedido(clienteMatheus);
        pedido.adicionarItem(new ItemPedido(10, pedido, produtoCelular));
        pedido.adicionarItem(new ItemPedido(40, pedido, produtoVideogame));
        //
        Pedido pedido2 = new Pedido(clienteMatheus);
        pedido.adicionarItem(new ItemPedido(2, pedido2, produtoMacbook));

        // Instancia EntityManager e DAOs
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);

        // Inicio transacao com BD
        em.getTransaction().begin();

        // Cadastra categorias
        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);

        // Cadastra produtos
        produtoDao.cadastrar(produtoCelular);
        produtoDao.cadastrar(produtoVideogame);
        produtoDao.cadastrar(produtoMacbook);

        // Cadastra clientes
        clienteDao.cadastrar(clienteMatheus);
        clienteDao.cadastrar(clienteMarcelo);
        clienteDao.cadastrar(clienteRodrigo);

        // Cadastra pedidos
        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido2);

        // Commit BD
        em.getTransaction().commit();

        // Fim transacao com BD
        em.close();
    }
}
