package com.example.pedro.appbioplantas.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Pergunta implements Serializable{

    int id;
    String pergunta;
    ArrayList<Resposta> respostas;

    public Pergunta(){
        respostas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public ArrayList<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(ArrayList<Resposta> respostas) {
        this.respostas = respostas;
    }
}
