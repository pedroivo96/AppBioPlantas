package com.example.pedro.appbioplantas.View;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pedro.appbioplantas.Controller.AdapterChaves;
import com.example.pedro.appbioplantas.Controller.AdapterRespostas;
import com.example.pedro.appbioplantas.Controller.BDControl;
import com.example.pedro.appbioplantas.Model.Chave;
import com.example.pedro.appbioplantas.Model.Pergunta;
import com.example.pedro.appbioplantas.Model.Resposta;
import com.example.pedro.appbioplantas.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ChavesActivity extends AppCompatActivity {

    private ArrayList<Chave> chaves;
    private ListView listViewChaves;
    private BDControl bdControl;
    private AdapterChaves adapterChaves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chave);

        bdControl = new BDControl(getContext());
        listViewChaves = findViewById(R.id.chaves);

        Intent intent = getIntent();
        int tipoChave = intent.getIntExtra("tipoChave",-1);

        if(tipoChave == 1){
            chaves = bdControl.getChavesPrimarias();
            Toast.makeText(getContext(), "Primarias: "+chaves.size(), Toast.LENGTH_SHORT).show();
        }

        if(tipoChave == 2){
            chaves = bdControl.getChavesSecundarias();
            Toast.makeText(getContext(), "Secundarias: "+chaves.size(), Toast.LENGTH_SHORT).show();
        }

        if(tipoChave == -1){
            Toast.makeText(getApplicationContext(), "Erro na escolha do tipo de chave!", Toast.LENGTH_SHORT).show();
            finish();
        }

        adapterChaves = new AdapterChaves(getContext(), chaves);
        listViewChaves.setAdapter(adapterChaves);

        listViewChaves.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), PerguntasActivity.class);
                intent.putExtra("NomeChaveSelecionada",chaves.get(i).getNome());
                intent.putExtra("IdChaveSelecionada", chaves.get(i).getId());
                startActivity(intent);
            }
        });
    }

    private Context getContext(){
        return this;
    }
}
