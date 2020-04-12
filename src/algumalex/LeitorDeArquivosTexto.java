/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algumalex;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
/**
 *
 * @author celson
 */
public class LeitorDeArquivosTexto {
    
    private final static int TAMANHO_BUFFER = 5;
    int[] bufferDeLeitura;
    int ponteiro;
    
    InputStream is;
    public LeitorDeArquivosTexto(String arquivo) {
        try {
            is = new FileInputStream(new File(arquivo));
            inicializarBuffer();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
   
    private void inicializarBuffer() {
        bufferDeLeitura = new int[TAMANHO_BUFFER * 2];
        ponteiro = 0;
        recarregarBuffer1();
    }
    
    private void incrementarPonteiro() {
        ponteiro++;
        if (ponteiro == TAMANHO_BUFFER) {
            recarregarBuffer2();
        } else if (ponteiro == TAMANHO_BUFFER * 2) {
            recarregarBuffer1();
            ponteiro = 0;
        }
    }
        private void recarregarBuffer1() {
        try {
            for (int i = 0; i < TAMANHO_BUFFER; i++) {
                bufferDeLeitura[i] = is.read();
                if (bufferDeLeitura[i] == -1) {
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
    private void recarregarBuffer2() {
        try {
            for (int i = TAMANHO_BUFFER; i < TAMANHO_BUFFER * 2; i++) {
                bufferDeLeitura[i] = is.read();
                if (bufferDeLeitura[i] == -1) {
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
    private int lerCaractereDoBuffer() {
        int ret = bufferDeLeitura[ponteiro];
        incrementarPonteiro();
        return ret;
    }
    
     public int lerProximoCaractere() {
        int c = lerCaractereDoBuffer();
        System.out.print((char)c);
        return c;
    }
    public void retroceder() {
        ponteiro--;
        if (ponteiro < 0) {
            ponteiro = TAMANHO_BUFFER * 2 - 1;
        }
    }
}
