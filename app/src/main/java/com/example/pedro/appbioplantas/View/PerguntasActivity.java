package com.example.pedro.appbioplantas.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pedro.appbioplantas.Controller.AdapterRespostas;
import com.example.pedro.appbioplantas.Controller.BDControl;
import com.example.pedro.appbioplantas.Model.Pergunta;
import com.example.pedro.appbioplantas.Model.Resposta;
import com.example.pedro.appbioplantas.R;

import java.util.ArrayList;

public class PerguntasActivity extends AppCompatActivity {

    private ListView respostasListView;
    private TextView enunciadoTextView;
    private String nomeChaveSelecionada;
    private int idChaveSelecionada;
    private ArrayList<Pergunta> perguntas = new ArrayList<>();
    private BDControl bdControl;
    private AdapterRespostas adapterRespostas;
    private ArrayList<Resposta> respostasAtuais;
    private ArrayList<String> respostasEscolhidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas);

        enunciadoTextView = findViewById(R.id.enunciadoPergunta);
        respostasListView = findViewById(R.id.respostas);

        Intent intent = getIntent();
        nomeChaveSelecionada = intent.getStringExtra("NomeChaveSelecionada");
        idChaveSelecionada = intent.getIntExtra("IdChaveSelecionada", -1);

        setTitle(nomeChaveSelecionada);

        respostasEscolhidas = new ArrayList<>();
        bdControl = new BDControl(getContext());
        perguntas = bdControl.getPerguntasByChave(idChaveSelecionada);

        atualizarPergunta(perguntas.get(0).getId()); //inicializa o questionário

        respostasListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                respostasEscolhidas.add(respostasAtuais.get(i).getOpcao());
                if(respostasAtuais.get(i).getProximaPergunta() == -1){
                    Intent intent = new Intent(getContext(), ResultadoActivity.class);
                    intent.putExtra("RespostasEscolhidas", respostasEscolhidas);
                    intent.putExtra("NomePlanta", respostasAtuais.get(i).getResultado());
                    startActivity(intent);
                    finish();
                }else{
                    atualizarPergunta(respostasAtuais.get(i).getProximaPergunta());
                }
            }
        });
    }

    private void atualizarPergunta(int idPergunta){

        Pergunta p =  getPerguntaById(idPergunta);
        if(p != null) enunciadoTextView.setText(p.getEnunciado());

        if(adapterRespostas == null){
            respostasAtuais = bdControl.getRespostasByPergunta(idChaveSelecionada, idPergunta);
            adapterRespostas = new AdapterRespostas(getContext(), respostasAtuais);
            respostasListView.setAdapter(adapterRespostas);
        }else{
            respostasAtuais.clear();
            respostasAtuais.addAll(bdControl.getRespostasByPergunta(idChaveSelecionada, idPergunta));
            adapterRespostas.notifyDataSetChanged();
        }
    }

    private Pergunta getPerguntaById(int idPergunta){
        for(Pergunta p : perguntas){
            if(p.getId() == idPergunta){
                return p;
            }
        }
        return null;
    }

    private Context getContext() {
        return this;
    }
}
