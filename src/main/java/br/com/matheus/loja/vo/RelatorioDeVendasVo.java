package br.com.matheus.loja.vo;

import java.time.LocalDate;

public class RelatorioDeVendasVo {

    private final String nomeProduto;
    private final Long quantidadeVendida;
    private final LocalDate dataUltimaVenda;

    public RelatorioDeVendasVo(String nomeProduto, Long quantidadeVendida, LocalDate dataUltimaVenda) {
        this.nomeProduto = nomeProduto;
        this.quantidadeVendida = quantidadeVendida;
        this.dataUltimaVenda = dataUltimaVenda;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Long getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public LocalDate getDataUltimaVenda() {
        return dataUltimaVenda;
    }

    @Override
    public String toString() {
        return "RelatorioDeVendasVo{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", quantidadeVendida=" + quantidadeVendida +
                ", dataUltimaVenda=" + dataUltimaVenda +
                '}';
    }
}
