package Classes;

public class Terminal {
    private String simbolo;

    public Terminal(String simbolo){
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    @Override
    public String toString() {
        return "\n          Terminal{" +
                "simbolo='" + simbolo + '\'' +
                '}';
    }
}
