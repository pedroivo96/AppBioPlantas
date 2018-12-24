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
import java.util.List;

public class ResultadoActivity extends AppCompatActivity implements Serializable{

    private TextView tNomePlanta;
    private ListView lPerguntasEscolhidas;
    private ArrayList<String> respostasEscolhidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        tNomePlanta = (TextView) findViewById(R.id.tNomePlanta);
        lPerguntasEscolhidas = (ListView) findViewById(R.id.lPerguntasEscolhidas);

        Intent intent = getIntent();
        String nomePlanta = intent.getStringExtra("NomePlanta");
        respostasEscolhidas = (ArrayList<String>) intent.getSerializableExtra("RespostasEscolhidas");

        tNomePlanta.setText(nomePlanta);

        AdapterRespostasEscolhidas adapterRespostasEscolhidas = new AdapterRespostasEscolhidas(getContext(), respostasEscolhidas);
        lPerguntasEscolhidas.setAdapter(adapterRespostasEscolhidas);
    }

    private Context getContext(){
        return this;
    }
}
