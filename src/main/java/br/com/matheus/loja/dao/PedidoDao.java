package br.com.matheus.loja.dao;

import br.com.matheus.loja.modelo.Pedido;
import br.com.matheus.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {
    private final EntityManager em;

    public PedidoDao(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Pedido pedido){
        this.em.persist(pedido);
    }

    public BigDecimal valorTotalVendido(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public List<Object[]> relatorioDeVendas(){
        String jpql = "SELECT produto.nome, " +
                "SUM(item.quantidade), " +
                "MAX(pedido.date) " +
                "FROM Pedido pedido " +
                "JOIN pedido.itens item " +
                "JOIN item.produto produto " +
                "GROUP BY produto.nome " +
                "ORDER BY item.quantidade DESC";
        return em.createQuery(jpql, Object[].class )
                .getResultList();

    }
    public List<RelatorioDeVendasVo> relatorioDeVendasVo(){
        String jpql = "SELECT new br.com.matheus.loja.vo.RelatorioDeVendasVo(" +
                "produto.nome, " +
                "SUM(item.quantidade), " +
                "MAX(pedido.date)) " +
                "FROM Pedido pedido " +
                "JOIN pedido.itens item " +
                "JOIN item.produto produto " +
                "GROUP BY produto.nome " +
                "ORDER BY item.quantidade DESC";
        return em.createQuery(jpql, RelatorioDeVendasVo.class )
                .getResultList();
    }

    // anti-lazyException (JOIN FETCH)
    public Pedido buscarPedidoComCliente(Long id){
        String jpql = "SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id";
        return em.createQuery(jpql, Pedido.class)
                .setParameter("id", id)
                .getSingleResult();
    }

}
