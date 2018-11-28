package Classes;

public class Variavel {
    private char nome;

    public Variavel(char nome){
        this.nome = nome;
    }

    public char getNome() {
        return nome;
    }

    public void setNome(char nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "\n          Variavel{" +
                "nome=" + nome +
                '}';
    }
}
