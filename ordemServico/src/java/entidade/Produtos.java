/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

/**
 *
 * @author Douglas
 */
public class Produtos {
    private OrdemDeServico OrdemDeServico_id;
    private Produto Produto_id;
    private int quantidade;

    public OrdemDeServico getOrdemDeServico_id() {
        return OrdemDeServico_id;
    }

    public void setOrdemDeServico_id(OrdemDeServico OrdemDeServico_id) {
        this.OrdemDeServico_id = OrdemDeServico_id;
    }

    public Produto getProduto_id() {
        return Produto_id;
    }

    public void setProduto_id(Produto Produto_id) {
        this.Produto_id = Produto_id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    
    
}
