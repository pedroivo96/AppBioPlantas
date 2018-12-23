package com.example.pedro.appbioplantas.Model;

import java.io.Serializable;

public class Resposta implements Serializable{

    private String opcao;
    private int proximaPergunta; //Se for -1 significado que nao tem proxima pergunta e então temos um resultado
    private String resultado; //Nome planta

    public Resposta(String opcao, String resposta) {
        this.opcao = opcao;
        if(isInteiro(resposta)) {
            this.proximaPergunta = Integer.valueOf(resposta);
        }else{
            this.proximaPergunta = -1;
            this.resultado = resposta;
        }
    }

    private boolean isInteiro(String s) {
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++ ){
            if (!Character.isDigit(c[i])) {
                return false;
            }
        }
        return true;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    public int getProximaPergunta() {
        return proximaPergunta;
    }

    public void setProximaPergunta(int proximaPergunta) {
        this.proximaPergunta = proximaPergunta;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
