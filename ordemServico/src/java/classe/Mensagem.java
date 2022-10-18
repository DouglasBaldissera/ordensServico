package classe;

import java.util.ArrayList;

public class Mensagem {

    private ArrayList<String> mensagens = new ArrayList();
    private int posicao = 0;

    public void adicionar(String mensagem) {
        this.mensagens.add(mensagem);
    }

    public String mensagem() {
        if (posicao < mensagens.size()) {
            return mensagens.get(posicao);
        }
        posicao++;
        return null;
    }

    public void apagarTodas() {
        mensagens.clear();
    }

    public int quantidade() {
        return mensagens.size();

    }
    
}
