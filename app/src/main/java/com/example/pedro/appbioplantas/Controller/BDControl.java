package com.example.pedro.appbioplantas.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pedro.appbioplantas.Model.*;

import java.util.ArrayList;

public class BDControl {

    private PlantasBD plantasBD;
    private SQLiteDatabase database;

    public BDControl(Context context){
        plantasBD = new PlantasBD(context);
        database = plantasBD.getReadableDatabase();
    }

    public ArrayList<Chave> getChavesPrimarias(){

        ArrayList<Chave> chaves = new ArrayList<>();

        String selectQuery = "SELECT id, nome FROM Chaves WHERE tipo = 1";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            while(! cursor.isAfterLast()) {
                chaves.add(new Chave(cursor.getInt(0), cursor.getString(1), 1));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return chaves;
    }

    public ArrayList<Chave> getChavesSecundarias(){

        ArrayList<Chave> chaves = new ArrayList<>();

        String selectQuery = "SELECT id, nome FROM Chaves WHERE tipo = 2";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            while(! cursor.isAfterLast()) {
                chaves.add(new Chave(cursor.getInt(0), cursor.getString(1), 1));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return chaves;
    }

    public ArrayList<Pergunta> getPerguntasByChave(int idChave){

        ArrayList<Pergunta> perguntas = new ArrayList<>();

        String selectQuery = "SELECT id_pergunta, enunciado FROM Perguntas WHERE id_chave = "+idChave;
        Cursor cursor = database.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            while(! cursor.isAfterLast()) {
                perguntas.add(new Pergunta(cursor.getInt(0), cursor.getString(1)));
                cursor.moveToNext();
            }
        }
        cursor.close();

        return perguntas;
    }

    public ArrayList<Resposta> getRespostasByPergunta(int idChave, int idPergunta){
        ArrayList<Resposta> respostas = new ArrayList<>();

        String selectQuery = "SELECT opcao, resposta FROM Opcoes WHERE id_chave = "+idChave+" and id_pergunta = "+idPergunta;
        Cursor cursor = database.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            while(! cursor.isAfterLast()) {
                respostas.add(new Resposta(cursor.getString(0), cursor.getString(1)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return respostas;
    }
}
