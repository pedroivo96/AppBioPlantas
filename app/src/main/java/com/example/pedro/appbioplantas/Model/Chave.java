package com.example.pedro.appbioplantas.Model;

import java.util.ArrayList;

public class Chave {

    private int id;
    private ArrayList<Pergunta> perguntas;

    public Chave(){
        perguntas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(ArrayList<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }
}
