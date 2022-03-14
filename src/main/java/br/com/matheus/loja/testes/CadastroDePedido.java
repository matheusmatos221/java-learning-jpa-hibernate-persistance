package br.com.matheus.loja.testes;

import br.com.matheus.loja.dao.CategoriaDao;
import br.com.matheus.loja.dao.ClienteDao;
import br.com.matheus.loja.dao.PedidoDao;
import br.com.matheus.loja.dao.ProdutoDao;
import br.com.matheus.loja.modelo.*;
import br.com.matheus.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {
    public static void main(String[] args) {
        popularBancoDeDados();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);

        Produto produto = produtoDao.buscarPorId(1L);  // Xiaomi RedMi
        Produto produto2 = produtoDao.buscarPorId(2L);  // PS5
        Produto produto3 = produtoDao.buscarPorId(3L);  // Macbook
        Cliente cliente = clienteDao.buscarPorId(1L);  // Matheus

        // Inicio transacao com BD
        em.getTransaction().begin();
        // Pedidos
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));
        pedido.adicionarItem(new ItemPedido(40, pedido, produto2));
        pedidoDao.cadastrar(pedido);

        Pedido pedido2 = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(2, pedido2, produto3));
        pedidoDao.cadastrar(pedido2);

        em.getTransaction().commit();

        BigDecimal totalVendido = pedidoDao.valorTotalVendido();
        System.out.println("VALOR TOTAL: "+totalVendido);

        List<Object[]> relatorio = pedidoDao.relatorioDeVendas();
        for(Object[] obj : relatorio){
            System.out.println(obj[0]);
            System.out.println(obj[1]);
            System.out.println(obj[2]);
        }
        // Fim transação com BD
        em.close();
    }
    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("Xiaomi RedMi", "Celular muito legal", new BigDecimal("1000"), celulares);
        Produto videogame = new Produto("PS5", "PlayStation 5", new BigDecimal("2000"), videogames);
        Produto macbook = new Produto("Macbook", "Macbook pro ", new BigDecimal("4000"), informatica);

        Cliente clienteMatheus = new Cliente("Matheus", "000001");
        Cliente clienteMarcelo = new Cliente("Marcelo", "000002");
        Cliente clienteRodrigo = new Cliente("Rodrigo", "000003");

        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        // Inicio transacao com BD
        em.getTransaction().begin();
        // Cadastra categorias
        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);
        // Cadastra produtos
        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videogame);
        produtoDao.cadastrar(macbook);
        // Cadastra clientes
        clienteDao.cadastrar(clienteMatheus);
        clienteDao.cadastrar(clienteMarcelo);
        clienteDao.cadastrar(clienteRodrigo);

        em.getTransaction().commit();
        // Fim transação com BD
        em.close();

    }
}
