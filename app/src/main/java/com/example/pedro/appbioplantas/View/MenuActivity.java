package com.example.pedro.appbioplantas.View;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pedro.appbioplantas.Model.Pergunta;
import com.example.pedro.appbioplantas.Model.Resposta;
import com.example.pedro.appbioplantas.R;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private Button bFicha;
    private Button bChave;
    private Button bCodigo;
    private Button bGuia;
    private Button bCadastro;

    private ArrayList<Pergunta> perguntas;
    private ArrayList<Pergunta> perguntasEscolhidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        perguntas = new ArrayList<>();
        perguntasEscolhidas = new ArrayList<>();
        //inicializarPerguntas();

        bFicha = findViewById(R.id.bFicha);
        bChave = findViewById(R.id.bChave);
        bCodigo = findViewById(R.id.bCodigo);
        bGuia = findViewById(R.id.bGuia);
        bCadastro = findViewById(R.id.bCadastro);

        bFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        bChave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Pergunta1Activity.class);
                intent.putExtra("perguntas", perguntas);
                intent.putExtra("perguntasEscolhidas", perguntasEscolhidas);

                startActivity(intent);
            }
        });

        bCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        bGuia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        bCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private Context getContext(){
        return this;
    }
}
