package com.example.pedro.appbioplantas.Model;

import java.io.Serializable;

public class Resposta implements Serializable{
    String enunciado;
    int proximaPergunta;//Se for 0 significado que nao tem proxima pergunta e então temos um resultado
    String resultado;//Nome planta

    public Resposta(String enunciado, int proximaPergunta, String resultado){
        this.enunciado = enunciado;
        this.proximaPergunta = proximaPergunta;
        this.resultado = resultado;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
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
