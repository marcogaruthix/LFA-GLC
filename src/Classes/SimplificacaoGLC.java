package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class SimplificacaoGLC {
    private Gramatica gramatica;

    public  SimplificacaoGLC(Gramatica gramatica){
        this.gramatica = gramatica;
    }

    //Exclusão de Produções Vazias (1/3)
    public Gramatica etapa1() throws Exception {

        // PARTE1
        //Variáveis que constituem produções vazias:
        //– Considera, inicialmente, todas as variáveis que geram diretamente 
        //(exemplo A → ). A seguir, sucessivamente são determinadas as
        //variáveis que indiretamente geram e (exemplo B → A).
        List<Producao> producoesGeramVazio = new ArrayList<Producao>();
        for(Producao p : this.gramatica.getProducoes()){
            if(GramaticaUtils.producaoGeraVazioDiretamenteIndiretamente(p, this.gramatica))
                producoesGeramVazio.add(p);
        }


        //PARTE 2
        //A
        //seguir, cada produção cujo lado direito possui uma variável que gera a
        //palavra vazia, determina uma produção adicional, sem esta variável.
        int i = 0;

        for(Producao p : this.gramatica.getProducoes()){
            //regras originais
            List<ProducaoRegra> producaoRegras = new ArrayList<ProducaoRegra>(p.getRegras().size());
            producaoRegras.addAll(p.getRegras());
            for (ProducaoRegra regra : producaoRegras){

                if(regra.getDerivacao() == "&"){
                    this.gramatica.getProducoes().get(i).getRegras().remove(regra);
                    continue;
                }

                //producoes vazias
                for (Producao producaoGeraVazio : producoesGeramVazio){
                    // se a regra original conter uma producao que gera vazio
                    if(regra.getDerivacao().contains( String.valueOf( producaoGeraVazio.getNome() ) )) {

                        if(regra.getDerivacao().length() == 1)
                            continue;

                        ProducaoRegra novaRegra = new ProducaoRegra(regra.getDerivacao().replace(String.valueOf( producaoGeraVazio.getNome() ), ""));
                        this.gramatica.getProducoes().get(i).getRegras().add(novaRegra);

                    }
                }
            }
            i++;
        }

        //PARTE3
        //Se a palavra vazia pertence à linguagem, então é incluída uma
        //produção para gerar a palavra vazia. (O VAZIO É ADICIONADO APENAS SE A VARIAVEL FOR A START!)
        for (Producao p : this.gramatica.getProducoes()){
            if(p.isGeraVazio() && p.getNome() == this.gramatica.getStart().getNome())
                p.getRegras().add(new ProducaoRegra("&"));
        }

        //etapa2();

        return this.gramatica;
    }

    //Exclusão das Produções da Forma A → B
    public Gramatica etapa2() throws Exception {
        int i = 0;
        Collections.reverse(this.gramatica.getProducoes());
        for (Producao p : this.gramatica.getProducoes()){

            List<ProducaoRegra> regras = new ArrayList<ProducaoRegra>(p.getRegras().size());
            regras.addAll(p.getRegras());

            for (ProducaoRegra pRegra : regras){

                if( !Pattern.compile("^[A-Z]$").matcher(pRegra.getDerivacao()).matches() )
                    continue;

                //List<ProducaoRegra> novasRegras = GramaticaUtils.getVariavelPeloNome(pRegra.getDerivacao().charAt(0), this.gramatica).getRegras();
                List<ProducaoRegra> novasRegras = GramaticaUtils.extrairRegrasDaProducaoInutil( GramaticaUtils.getVariavelPeloNome(pRegra.getDerivacao().charAt(0), this.gramatica), this.gramatica );
                this.gramatica.getProducoes().get(i).getRegras().remove(pRegra);
                this.gramatica.getProducoes().get(i).getRegras().addAll(novasRegras);
            }
            i++;
        }
        Collections.reverse(this.gramatica.getProducoes());

        return this.gramatica;
    }

    // Exclusão de Variaveis Inuteis (Nao geram palavras)
    public Gramatica etapa3(){

        // IDENTIFICANDO VARIAVEIS INUTEIS (NAO GERAM NENHUM TERMINAL DIRETAMENTE)
        List<Variavel> novasVariaveis = new ArrayList<Variavel>();
        int i = 0;
        for (Producao p : this.gramatica.getProducoes()){

            //if(p.getNome() == this.gramatica.getStart().getNome())
            //    continue;

            List<ProducaoRegra> regras = new ArrayList<ProducaoRegra>(p.getRegras().size());
            regras.addAll(p.getRegras());

            regrasLoop:
            {
                for (ProducaoRegra pRegra : regras) {

                    // Qualquer simbolo exceto UpperCase
                    if (Pattern.compile("[^A-Z]").matcher(pRegra.getDerivacao()).matches() && pRegra.getDerivacao().length() > 0) {
                        novasVariaveis.add(new Variavel(p.getNome()));
                        break regrasLoop;
                    }

                }
            }
            i++;
        }

        this.gramatica.setVariaveis(novasVariaveis);

        // REMOVENDO PRODUCOES (VARIAVEIS INUTEIS) E SETANDO NOVOS TERMINAIS
        this.gramatica.setTerminais( new ArrayList<Terminal>() );
        List<Producao> novasProducoes = new ArrayList<Producao>();
        List<Producao> gramaticaProducoes = new ArrayList<Producao>();
        gramaticaProducoes.addAll(this.gramatica.getProducoes());
        for(Producao p : gramaticaProducoes)
            for(Variavel v : novasVariaveis)
                if(p.getNome() == v.getNome()) {
                    novasProducoes.add(p);

                    // SETANDO NOVOS TERMINAIS
                    for (ProducaoRegra regra : p.getRegras()){
                        List<Terminal> pTerminais = GramaticaUtils.extrairTerminaisDaProducaoRegra(regra);
                        for (Terminal t : pTerminais){
                            if(!GramaticaUtils.gramaticaPossuiTerminal(this.gramatica, t))
                                this.gramatica.getTerminais().add(t);
                        }
                    }

                }
        this.gramatica.setProducoes(novasProducoes);

        return this.gramatica;
    }
}
