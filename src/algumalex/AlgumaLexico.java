/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algumalex;

/**
 *
 * @author celson
 */
public class AlgumaLexico {
    LeitorDeArquivosTexto ldat;
    public AlgumaLexico(String arquivo) {
        ldat = new LeitorDeArquivosTexto(arquivo);
    }
    public Token proximoToken() {
        int caractereLido = -1;
        while((caractereLido = ldat.lerProximoCaractere()) != -1) {
            char c = (char)caractereLido;
            if(c == ' ' || c == '\n') continue;
            
            
            if(c == ':') {
                return new Token(TipoToken.Delim,":");
            }
            else if(c == '*') {
                return new Token(TipoToken.OpAritMult,"*");
            }
            else if(c == '/') {
                return new Token(TipoToken.OpAritDiv,"/");
            }
            else if(c == '+') {
                return new Token(TipoToken.OpAritSoma,"+");
            }
            else if(c == '-') {
                return new Token(TipoToken.OpAritSub,"-");
            }
            else if(c == '(') {
                return new Token(TipoToken.AbrePar,"(");
            }
            else if(c == ')') {
                return new Token(TipoToken.FechaPar,")");
            }
            else if(c == '<') {
                c = (char)ldat.lerProximoCaractere();
                if(c == '>')
                    return new Token(TipoToken.OpRelDif,"<>");
                else if(c == '=')
                    return new Token(TipoToken.OpRelMenorIgual,"<=");
                else return new Token(TipoToken.OpRelMenor,"<");
            }
            
        }
        return null;
    }
}
