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

    private Button bLogin;
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

        bLogin = findViewById(R.id.bLogin);
        bCadastro = findViewById(R.id.bCadastro);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        bCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CadastroActivity.class);
                startActivity(intent);
            }
        });
    }

    private Context getContext(){
        return this;
    }
}
