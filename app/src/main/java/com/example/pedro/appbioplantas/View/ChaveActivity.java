package com.example.pedro.appbioplantas.View;

import android.content.Context;
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

import com.example.pedro.appbioplantas.Controller.AdapterRespostas;
import com.example.pedro.appbioplantas.Model.Chave;
import com.example.pedro.appbioplantas.Model.Pergunta;
import com.example.pedro.appbioplantas.Model.Resposta;
import com.example.pedro.appbioplantas.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ChaveActivity extends AppCompatActivity {

    private ArrayList<Chave> chaves;
    private TextView enunciado;
    private ListView respostas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chave);

        enunciado = findViewById(R.id.enunciadoPergunta);
        respostas = findViewById(R.id.respostas);

        try {
            chaves = construirChaves();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int chaveAtualIndex = 2;
        Chave chaveAtual = new Chave();

        for(int i = 0; i < chaves.size(); i++){
            if(chaveAtualIndex == chaves.get(i).getId()){
                chaveAtual = chaves.get(i);
            }
        }

        final Pergunta perguntaAtual = chaveAtual.getPerguntas().get(0);
        enunciado.setText(perguntaAtual.getPergunta());

        AdapterRespostas adapterRespostas = new AdapterRespostas(getContext(),
                                                                 perguntaAtual.getRespostas());

        respostas.setAdapter(adapterRespostas);
        final Chave finalChaveAtual = chaveAtual;

        respostas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Resposta resposta = perguntaAtual.getRespostas().get(i);
                int proximaPergunta = resposta.getProximaPergunta();

                if(proximaPergunta == -1){
                    //É resultado. Exibir a família do vegetal
                    Toast.makeText(ChaveActivity.this, "Família do vegetal é : "+resposta.getResultado(), Toast.LENGTH_SHORT).show();
                }
                else{
                    //Não é resultado. Exibir a próxima pergunta

                    Pergunta p = new Pergunta();

                    for(int j = 0; j < finalChaveAtual.getPerguntas().size(); j++){

                        if(proximaPergunta == finalChaveAtual.getPerguntas().get(j).getId()){
                            p = finalChaveAtual.getPerguntas().get(j);
                        }
                    }

                    enunciado.setText(p.getPergunta());

                    AdapterRespostas adapterRespostas = new AdapterRespostas(getContext(), p.getRespostas());

                    respostas.setAdapter(adapterRespostas);
                }
            }
        });
    }

    private ArrayList<Chave> construirChaves() throws IOException {

        ArrayList<Chave> chaves = new ArrayList<>();
        ArrayList<String> mLines = new ArrayList<>();
        AssetManager am = getContext().getAssets();

        String path = "chave_dois.txt";

        try {
            InputStream is = am.open(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null) {

                if(line.length() != 0) {
                    mLines.add(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        int codChaveAtual = 0;

        for(int i = 0; i < mLines.size(); i++){

            //Se o primeiro caractere da linha for '>', então é uma Pergunta
            if (mLines.get(i).charAt(0) == '>') {

                Pergunta pergunta = new Pergunta();

                //Quebra a string para obter cada uma de suas partes
                String[] array = mLines.get(i).split("-");

                //Obtém a parte da string referente ao código da chave e da pergunta
                String codChavecodPerg = array[0].substring(1).trim();

                //Retira os espaços no início e no fim da parte referente ao ENUNCIADO
                String enunciado = array[1].trim();

                String[] array1 = codChavecodPerg.split("/");

                //Obter o código da Chave
                int codChave = Integer.parseInt(array1[0].trim());

                //Obter o código da Pergunta
                int codPergunta = Integer.parseInt(array1[1].trim());

                pergunta.setId(codPergunta);
                pergunta.setPergunta(enunciado);

                //Se o ID da Chave da pergunta for diferente da chaveAtual então é uma nova Chave
                if (codChaveAtual != codChave) {

                    Chave c = new Chave();
                    c.setId(codChave);

                    codChaveAtual = codChave;

                    chaves.add(c);
                }

                int indexUltimaChave = chaves.size() - 1;

                chaves.get(indexUltimaChave).getPerguntas().add(pergunta);
            }

            //Se o primeiro caractere da linha for '*', então é uma resposta
            else {

                String[] array = mLines.get(i).split("-");
                String enunciado = array[0].substring(1).trim().replaceAll("_", " ");

                int proximaPergunta;
                String resultado = "";

                try{
                    proximaPergunta = Integer.parseInt(array[1].trim());
                }catch(Exception e){
                    proximaPergunta = -1;
                    resultado = array[1].trim();
                }

                Resposta r = new Resposta();
                r.setEnunciado(enunciado);
                r.setProximaPergunta(proximaPergunta);
                r.setResultado(resultado);

                int indexUltimaChave = chaves.size() - 1;
                int indexUltimaPergunta = chaves.get(indexUltimaChave).getPerguntas().size() - 1;

                chaves.get(indexUltimaChave).getPerguntas().get(indexUltimaPergunta).getRespostas().add(r);
            }
        }

        return chaves;
    }

    private Context getContext(){
        return this;
    }
}
