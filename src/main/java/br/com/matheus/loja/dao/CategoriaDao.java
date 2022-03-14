package br.com.matheus.loja.dao;

import br.com.matheus.loja.modelo.Categoria;

import javax.persistence.EntityManager;

public class CategoriaDao {
    private final EntityManager em;

    public CategoriaDao(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Categoria categoria){
        this.em.persist(categoria);
    }

}
