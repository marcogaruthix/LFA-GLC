package Classes;

public class ProducaoRegra {
    private String derivacao;

    public ProducaoRegra(String derivacao){
        this.derivacao = derivacao;
    }

    public String getDerivacao() {
        return derivacao;
    }

    public void setDerivacao(String derivacao) {
        this.derivacao = derivacao;
    }

    @Override
    public String toString() {
        return "\n               ProducaoRegra{" +
                "derivacao='" + derivacao + '\'' +
                '}';
    }
}
