package br.com.matheus.loja.testes;

import br.com.matheus.loja.dao.ProdutoDao;
import br.com.matheus.loja.modelo.Produto;
import br.com.matheus.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDeProduto {
    public static void main(String[] args) {
        Produto celular = new Produto();
        celular.setNome("Xiaomi RedMi");
        celular.setDescricao("Celular muito legal");
        celular.setPreco(new BigDecimal("800"));

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDAO = new ProdutoDao(em);

        em.getTransaction().begin();
        produtoDAO.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }
}
