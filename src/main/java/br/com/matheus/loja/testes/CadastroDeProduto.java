package br.com.matheus.loja.testes;

import br.com.matheus.loja.dao.CategoriaDao;
import br.com.matheus.loja.dao.ProdutoDao;
import br.com.matheus.loja.modelo.Categoria;
import br.com.matheus.loja.modelo.Produto;
import br.com.matheus.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDeProduto {
    public static void main(String[] args) {
        Categoria celulares = new Categoria("CELULAR");
        Produto celular = new Produto("Xiaomi RedMi", "Celular muito legal", new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
    }
}
