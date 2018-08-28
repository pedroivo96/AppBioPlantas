package com.example.pedro.appbioplantas.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pedro.appbioplantas.Controller.AdapterRespostasEscolhidas;
import com.example.pedro.appbioplantas.Model.Resposta;
import com.example.pedro.appbioplantas.R;

import java.io.Serializable;
import java.util.ArrayList;

public class ResultadoActivity extends AppCompatActivity implements Serializable{

    private TextView tNomePlanta;
    private ListView lPerguntasEscolhidas;

    private ArrayList<Resposta> respostasEscolhidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        Intent intent = getIntent();

        String nomePlanta = intent.getStringExtra("nomePlanta");
        respostasEscolhidas = (ArrayList<Resposta>) intent.getSerializableExtra("respostasEscolhidas");

        tNomePlanta = findViewById(R.id.tNomePlanta);
        lPerguntasEscolhidas = findViewById(R.id.lPerguntasEscolhidas);

        AdapterRespostasEscolhidas adapterRespostasEscolhidas = new AdapterRespostasEscolhidas(getContext(),respostasEscolhidas);
        lPerguntasEscolhidas.setAdapter(adapterRespostasEscolhidas);

        tNomePlanta.setText(nomePlanta);

    }

    private Context getContext(){
        return this;
    }
}
