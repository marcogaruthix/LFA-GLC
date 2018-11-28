package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GramaticaUtils {

    public static boolean producaoGeraVazioDiretamente(Producao p){

        for(ProducaoRegra regra : p.getRegras()){
            if(regra.getDerivacao().contains("&")){
                return true;
            }
        }

        return false;
    }

    public static boolean producaoGeraVazioDiretamenteIndiretamente(Producao p, Gramatica gramatica) throws Exception {

        for(ProducaoRegra regra : p.getRegras()){

            if( producaoGeraVazioDiretamente(p) ){
                return true;
            }

            if( existeProducaoNaRegra(regra) )
                for( Producao pLinha : getVariaveisByProducaoRegra(regra, gramatica) )
                    if( producaoGeraVazioDiretamenteIndiretamente ( pLinha, gramatica ) )
                        return true;

        }

        return false;
    }

    public static List<Producao> getVariaveisByProducaoRegra(ProducaoRegra regra, Gramatica gramatica) throws Exception {

        //if( regra.getTipo() == ProducaoRegraEnum.t || regra.getTipo() == ProducaoRegraEnum.tt )
        if( !existeProducaoNaRegra(regra) )
            throw new Exception("getVariaveisByProducaoRegra: O tipo da regra nao possui nenhuma variavel");

        List<Producao> producoes = new ArrayList<Producao>();
        for (char derivacaoChar : regra.getDerivacao().toCharArray()){
            if (Character.isUpperCase(derivacaoChar))
                producoes.add( getVariavelPeloNome(derivacaoChar, gramatica) );
        }

        return producoes;
    }

    public static Producao getVariavelPeloNome(char nome, Gramatica gramatica) throws Exception {
        
        for(Producao p : gramatica.getProducoes())
            if(p.getNome() == nome)
                return p;

        throw new Exception("getVariavelPeloNome: Producao(variavel) nao encontrada");
    }

    public static List<ProducaoRegra> removerProducaoDasRegras(List<ProducaoRegra> regras, Producao p){
        List<ProducaoRegra> novasRegras = new ArrayList<ProducaoRegra>();
        for(ProducaoRegra regra : regras){
            regra.setDerivacao( regra.getDerivacao().replace( String.valueOf(p.getNome()), "" ) );
            if(regra.getDerivacao() == "")
                continue;
            else
                novasRegras.add( regra );
        }

        return novasRegras;
    }

    public static boolean existeProducaoNaRegra(ProducaoRegra regra){
        Pattern pattern = Pattern.compile("[A-Z]");
        if( pattern.matcher(regra.getDerivacao()).matches() )
            return true;
        return false;
    }

    public static List<ProducaoRegra> extrairRegrasDaProducaoInutil(Producao p, Gramatica gramatica) throws Exception {

        if(p.getRegras().size() == 0)
            return p.getRegras();

        // Se a regra possuir apenas uma Variavel
        if( Pattern.compile("^[A-Z]$").matcher( p.getRegras().get(0).getDerivacao() ).matches() )
            return  extrairRegrasDaProducaoInutil( getVariavelPeloNome( p.getRegras().get(0).getDerivacao().charAt(0), gramatica ), gramatica );

        else
            return p.getRegras();
    }

    public static List<Terminal> extrairTerminaisDaProducaoRegra(ProducaoRegra regra){
        List<Terminal> terminais = new ArrayList<Terminal>();
        String[] terminaisString = regra.getDerivacao().replaceAll("[A-Z]", "@/@").split("@/@");
        for(String terminalString : terminaisString){
            if(terminalString.isEmpty() || terminalString.isBlank())
                continue;

            terminais.add(new Terminal(terminalString));
        }

        return terminais;
    }

    public static boolean gramaticaPossuiTerminal(Gramatica gramatica, Terminal terminal){
        for(Terminal t : gramatica.getTerminais()){
            if(t.getSimbolo().equals(terminal.getSimbolo()))
                return true;
        }

        return false;
    }
}
