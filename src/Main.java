import Classes.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        provaMalopes();
        //System.out.println(etapa3().toString());
    }

    public static void etapa1() throws Exception {

        List<Terminal> terminais = new ArrayList<Terminal>();
        terminais.add(new Terminal("a"));
        terminais.add(new Terminal("b"));

        List<Variavel> variaveis = new ArrayList<Variavel>();
        variaveis.add(new Variavel('S'));
        variaveis.add(new Variavel('X'));
        variaveis.add(new Variavel('Y'));

        List<ProducaoRegra> regrasS = new ArrayList<ProducaoRegra>();
        regrasS.add(new ProducaoRegra("aXa"));
        regrasS.add(new ProducaoRegra("bXb"));
        regrasS.add(new ProducaoRegra("&"));

        List<ProducaoRegra> regrasX = new ArrayList<ProducaoRegra>();
        regrasX.add(new ProducaoRegra("a"));
        regrasX.add(new ProducaoRegra("b"));
        regrasX.add(new ProducaoRegra("Y"));

        List<ProducaoRegra> regrasY = new ArrayList<ProducaoRegra>();
        regrasY.add(new ProducaoRegra("&"));

        List<Producao> producoes = new ArrayList<Producao>();
        producoes.add(new Producao('S', regrasS, true));
        producoes.add(new Producao('X', regrasX, false));
        producoes.add(new Producao('Y', regrasY, true));

        Variavel start = variaveis.get(0);

        Gramatica gramatica = new Gramatica(variaveis, terminais, producoes, start);

        SimplificacaoGLC simplificacaoGLC = new SimplificacaoGLC(gramatica);
        simplificacaoGLC.etapa1();
    }

    public static void etapa2() throws Exception {

        List<Terminal> terminais = new ArrayList<Terminal>();
        terminais.add(new Terminal("a"));
        terminais.add(new Terminal("b"));

        List<Variavel> variaveis = new ArrayList<Variavel>();
        variaveis.add(new Variavel('S'));
        variaveis.add(new Variavel('X'));

        List<ProducaoRegra> regrasS = new ArrayList<ProducaoRegra>();
        regrasS.add(new ProducaoRegra("aXa"));
        regrasS.add(new ProducaoRegra("bXb"));

        List<ProducaoRegra> regrasX = new ArrayList<ProducaoRegra>();
        regrasX.add(new ProducaoRegra("a"));
        regrasX.add(new ProducaoRegra("b"));
        regrasX.add(new ProducaoRegra("S"));
        regrasX.add(new ProducaoRegra("&"));

        List<Producao> producoes = new ArrayList<Producao>();
        producoes.add(new Producao('S', regrasS, false));
        producoes.add(new Producao('X', regrasX, true));

        Variavel start = variaveis.get(0);

        Gramatica gramatica = new Gramatica(variaveis, terminais, producoes, start);

        SimplificacaoGLC simplificacaoGLC = new SimplificacaoGLC(gramatica);
        simplificacaoGLC.etapa2();
    }

    public static Gramatica etapa3() throws Exception {

        List<Terminal> terminais = new ArrayList<Terminal>();
        terminais.add(new Terminal("a"));
        terminais.add(new Terminal("b"));
        terminais.add(new Terminal("c"));

        List<Variavel> variaveis = new ArrayList<Variavel>();
        variaveis.add(new Variavel('S'));
        variaveis.add(new Variavel('A'));
        variaveis.add(new Variavel('B'));
        variaveis.add(new Variavel('C'));

        List<ProducaoRegra> regrasS = new ArrayList<ProducaoRegra>();
        regrasS.add(new ProducaoRegra("aAa"));
        regrasS.add(new ProducaoRegra("bBb"));

        List<ProducaoRegra> regrasA = new ArrayList<ProducaoRegra>();
        regrasA.add(new ProducaoRegra("A"));
        regrasA.add(new ProducaoRegra("S"));

        List<ProducaoRegra> regrasB = new ArrayList<ProducaoRegra>();

        List<ProducaoRegra> regrasC = new ArrayList<ProducaoRegra>();
        regrasC.add(new ProducaoRegra("c"));

        List<Producao> producoes = new ArrayList<Producao>();
        producoes.add(new Producao('S', regrasS, false));
        producoes.add(new Producao('A', regrasA, false));
        producoes.add(new Producao('B', regrasB, false));
        producoes.add(new Producao('C', regrasC, false));

        Variavel start = variaveis.get(0);

        Gramatica gramatica = new Gramatica(variaveis, terminais, producoes, start);

        System.out.println("Gramatica Original: ");
        System.out.println(gramatica);

        SimplificacaoGLC simplificacaoGLC = new SimplificacaoGLC(gramatica);
        simplificacaoGLC.etapa3();

        return gramatica;
    }

    public static void test() throws Exception {

        List<Terminal> terminais = new ArrayList<Terminal>();
        terminais.add(new Terminal("a"));
        terminais.add(new Terminal("b"));
        terminais.add(new Terminal("c"));
        terminais.add(new Terminal("d"));

        List<Variavel> variaveis = new ArrayList<Variavel>();
        variaveis.add(new Variavel('S'));
        variaveis.add(new Variavel('A'));
        variaveis.add(new Variavel('B'));
        variaveis.add(new Variavel('C'));

        List<ProducaoRegra> regrasS = new ArrayList<ProducaoRegra>();
        regrasS.add(new ProducaoRegra("AB"));
        regrasS.add(new ProducaoRegra("SCB"));


        List<ProducaoRegra> regrasA = new ArrayList<ProducaoRegra>();
        regrasA.add(new ProducaoRegra("aA"));
        regrasA.add(new ProducaoRegra("C"));

        List<ProducaoRegra> regrasB = new ArrayList<ProducaoRegra>();
        regrasB.add(new ProducaoRegra("bB"));
        regrasB.add(new ProducaoRegra("b"));

        List<ProducaoRegra> regrasC = new ArrayList<ProducaoRegra>();
        regrasC.add(new ProducaoRegra("cC"));
        regrasC.add(new ProducaoRegra("&"));

        List<Producao> producoes = new ArrayList<Producao>();
        producoes.add(new Producao('S', regrasS, false));
        producoes.add(new Producao('A', regrasA, false));
        producoes.add(new Producao('B', regrasB, false));
        producoes.add(new Producao('C', regrasC, true));

        Variavel start = variaveis.get(0);

        Gramatica gramatica = new Gramatica(variaveis, terminais, producoes, start);

        SimplificacaoGLC simplificacaoGLC = new SimplificacaoGLC(gramatica);
        Gramatica novaGramatica = simplificacaoGLC.etapa1();
        novaGramatica = simplificacaoGLC.etapa2();
        novaGramatica = simplificacaoGLC.etapa3();
        System.out.println(novaGramatica);
    }

    public static void provaMalopes() throws Exception {

        List<Terminal> terminais = new ArrayList<Terminal>();
        terminais.add(new Terminal("a"));
        terminais.add(new Terminal("b"));
        terminais.add(new Terminal("c"));
        terminais.add(new Terminal("d"));

        List<Variavel> variaveis = new ArrayList<Variavel>();
        variaveis.add(new Variavel('S'));
        variaveis.add(new Variavel('A'));
        variaveis.add(new Variavel('B'));
        variaveis.add(new Variavel('C'));
        variaveis.add(new Variavel('D'));

        List<ProducaoRegra> regrasS = new ArrayList<ProducaoRegra>();
        regrasS.add(new ProducaoRegra("AB"));
        regrasS.add(new ProducaoRegra("A"));
        regrasS.add(new ProducaoRegra("CAa"));

        List<ProducaoRegra> regrasA = new ArrayList<ProducaoRegra>();
        regrasA.add(new ProducaoRegra("a"));
        regrasA.add(new ProducaoRegra("b"));

        List<ProducaoRegra> regrasB = new ArrayList<ProducaoRegra>();
        regrasB.add(new ProducaoRegra("BC"));
        regrasB.add(new ProducaoRegra("AB"));
        regrasB.add(new ProducaoRegra("bb"));
        regrasB.add(new ProducaoRegra("&"));

        List<ProducaoRegra> regrasC = new ArrayList<ProducaoRegra>();
        regrasC.add(new ProducaoRegra("cC"));
        regrasC.add(new ProducaoRegra("aC"));

        List<ProducaoRegra> regrasD = new ArrayList<ProducaoRegra>();
        regrasD.add(new ProducaoRegra("dD"));
        regrasD.add(new ProducaoRegra("d"));
        regrasD.add(new ProducaoRegra("c"));


        List<Producao> producoes = new ArrayList<Producao>();
        producoes.add(new Producao('S', regrasS, false));
        producoes.add(new Producao('A', regrasA, false));
        producoes.add(new Producao('B', regrasB, false));
        producoes.add(new Producao('C', regrasC, false));
        producoes.add(new Producao('D', regrasD, false));

        Variavel start = variaveis.get(0);

        Gramatica gramatica = new Gramatica(variaveis, terminais, producoes, start);

        SimplificacaoGLC simplificacaoGLC = new SimplificacaoGLC(gramatica);
        Gramatica novaGramatica = simplificacaoGLC.etapa1();
        novaGramatica = simplificacaoGLC.etapa2();
        novaGramatica = simplificacaoGLC.etapa3();
        System.out.println(novaGramatica);
    }
}
