package com.example.pedro.appbioplantas.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Pergunta implements Serializable{
    int id;
    int numChave;
    ArrayList<Resposta> respostas;

    public Pergunta(int id, int numChave, ArrayList<Resposta> respostas){
        this.id = id;
        this.numChave = numChave;
        this.respostas = respostas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumChave() {
        return numChave;
    }

    public void setNumChave(int numChave) {
        this.numChave = numChave;
    }

    public ArrayList<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(ArrayList<Resposta> respostas) {
        this.respostas = respostas;
    }
}
