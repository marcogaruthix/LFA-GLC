package Classes;

import java.util.List;

// ( {V}, {T}, {P}, S )
public class Gramatica {
    private List<Variavel> variaveis;
    private List<Terminal> terminais;
    private List<Producao> producoes;
    private Variavel start;

    public Gramatica(List<Variavel> variaveis, List<Terminal> terminais, List<Producao> producoes, Variavel start){
        this.variaveis = variaveis;
        this.terminais = terminais;
        this.producoes = producoes;
        this.start = start;
    }

    public List<Variavel> getVariaveis() {
        return variaveis;
    }

    public void setVariaveis(List<Variavel> variaveis) {
        this.variaveis = variaveis;
    }

    public List<Terminal> getTerminais() {
        return terminais;
    }

    public void setTerminais(List<Terminal> terminais) {
        this.terminais = terminais;
    }

    public List<Producao> getProducoes() {
        return producoes;
    }

    public void setProducoes(List<Producao> producoes) {
        this.producoes = producoes;
    }

    public Variavel getStart() {
        return start;
    }

    public void setStart(Variavel start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "\nGramatica{" +
                "\n     variaveis=" + variaveis +
                "\n,    terminais=" + terminais +
                "\n,    producoes=" + producoes +
                "\n,    start=" + start +
                "\n}";
    }
}
