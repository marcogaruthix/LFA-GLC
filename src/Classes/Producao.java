package Classes;

import java.util.List;

public class Producao {
    private char nome; //letra
    private List<ProducaoRegra> regras;
    private boolean geraVazio;

    public Producao(char nome, List<ProducaoRegra> regras, boolean geraVazio){
        this.setNome(nome);
        this.setRegras(regras);
        this.setGeraVazio(geraVazio);
    }

    public char getNome() {
        return nome;
    }

    public void setNome(char nome) {
        this.nome = nome;
    }

    public List<ProducaoRegra> getRegras() {
        return regras;
    }

    public void setRegras(List<ProducaoRegra> regras) {
        this.regras = regras;
    }

    public boolean isGeraVazio() {
        return geraVazio;
    }

    public void setGeraVazio(boolean geraVazio) {
        this.geraVazio = geraVazio;
    }

    @Override
    public String toString() {
        return "\n          Producao{" +
                "nome=" + nome +
                ", geraVazio=" + geraVazio +
                ", regras=" + regras +
                "\n}\n";
    }
}
