package com.example.pedro.appbioplantas.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Pergunta implements Serializable{

    private int id;
    private String enunciado;

    public Pergunta(int id, String enunciado) {
        this.id = id;
        this.enunciado = enunciado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
}
