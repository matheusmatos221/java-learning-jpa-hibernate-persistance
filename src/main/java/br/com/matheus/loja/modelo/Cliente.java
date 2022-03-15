package br.com.matheus.loja.modelo;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private DadosPessoais dadosPessoais;

    public Cliente(){
    }

    public Cliente(DadosPessoais dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    }

    public Cliente(String nome, String cpf){
        this.dadosPessoais = new DadosPessoais(nome, cpf);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public String getNome() {
        return this.getDadosPessoais().getNome();
    }

    public String getCpf() {
        return this.getDadosPessoais().getCpf();
    }

}