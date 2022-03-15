package br.com.matheus.loja.testes;

import br.com.matheus.loja.dao.CategoriaDao;
import br.com.matheus.loja.dao.ProdutoDao;
import br.com.matheus.loja.modelo.Categoria;
import br.com.matheus.loja.modelo.Produto;
import br.com.matheus.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
    public static void main(String[] args) {
        cadastrarProduto();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto p = produtoDao.buscarPorId(1L);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULAR");
        //List<Produto> todos = produtoDao.buscarPorNomeDaCategoria2("CELULAR");
        todos.forEach(p2 -> System.out.println(p.getNome()));

        BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi RedMi");
        System.out.println("Preco do Produto: " + precoDoProduto);

    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULAR");
        Produto celular = new Produto("Xiaomi RedMi", "Celular muito legal", new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();
//        Categoria categoria = em.find(Categoria.class, new Categoria("CELULARES", 'xpto'));

        em.close();
    }
}
