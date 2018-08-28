package com.example.pedro.appbioplantas.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pedro.appbioplantas.Controller.AdapterRespostas;
import com.example.pedro.appbioplantas.Model.Pergunta;
import com.example.pedro.appbioplantas.Model.Resposta;
import com.example.pedro.appbioplantas.R;

import java.util.ArrayList;

public class Pergunta1Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<Pergunta> perguntas;
    private ArrayList<Resposta> respostasEscolhidas;
    private Pergunta perguntaAtual;

    private ListView respostas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        respostasEscolhidas = new ArrayList<>();

        inicializarPerguntas();

        respostas = findViewById(R.id.respostas);


        //Caso o array de respostasEscolhidas esteja vazio, significa que devemos começar com a Pergunta de Numero1
        if(respostasEscolhidas.size() == 0){
            perguntaAtual = perguntas.get(0);
        }

        AdapterRespostas adapterRespostas = new AdapterRespostas(getContext(), perguntaAtual.getRespostas());
        respostas.setAdapter(adapterRespostas);

        respostas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Resposta r = perguntaAtual.getRespostas().get(i);

                respostasEscolhidas.add(r);

                if(r.getProximaPergunta() == 0){
                    //Chegamos a um resultado

                    String nomePlanta = r.getResultado();

                    Intent intent = new Intent(getContext(), ResultadoActivity.class);
                    intent.putExtra("nomePlanta", nomePlanta);
                    intent.putExtra("respostasEscolhidas", respostasEscolhidas)
                    ;                   startActivity(intent);
                }
                else{

                    int idProximaPergunta = r.getProximaPergunta();

                    perguntaAtual = obterPerguntaPorId(idProximaPergunta);

                    AdapterRespostas adapterRespostas = new AdapterRespostas(getContext(), perguntaAtual.getRespostas());
                    respostas.setAdapter(adapterRespostas);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Context getContext(){
        return this;
    }

    private void inicializarPerguntas(){

        perguntas = new ArrayList<>();

        Resposta r1 = new Resposta("Folhas pinatifidas, pinatisectas ou pseudo compostas", 0, "Cycadaceae");
        Resposta r2 = new Resposta("Folhas flabeliformes ou lobadas", 0, "Ginkgoaceae");
        Resposta r3 = new Resposta("Folhas inteiras ou reduzidas a escamas", 2, "");
        ArrayList<Resposta> respostas = new ArrayList<>();
        respostas.add(r1);
        respostas.add(r2);
        respostas.add(r3);
        Pergunta p1 = new Pergunta(1,2,respostas);
        perguntas.add(p1);

        r1 = new Resposta("Folhas alternas, espiraladas aos pares ou em feixes",3,"");
        r2 = new Resposta("Folhas opostas", 7, "");
        r3 = new Resposta("Folhas verticiladas", 0, "Cupressaceae");
        respostas = new ArrayList<>();
        respostas.add(r1);
        respostas.add(r2);
        respostas.add(r3);
        Pergunta p2 = new Pergunta(2,2, respostas);
        perguntas.add(p2);

        r1 = new Resposta("Folhas ovaes, eliticas, lanceoladas, deltoides ou de base assimétrica", 0,"");
        r2 = new Resposta("Folhas oblongas as vezes um pouco curvas", 4, "");
        r3 = new Resposta("Folhas aciculares", 0, "");
        respostas = new ArrayList<>();
        respostas.add(r1);
        respostas.add(r2);
        respostas.add(r3);
        Pergunta p3 = new Pergunta(3,2, respostas);
        perguntas.add(p3);

        r1 = new Resposta("Folhas até 2 milímetros de largura", 0, "Taxodiaceae");
        r2 = new Resposta("Folhas de mais de 3 milímetros de largura", 0, "Podocarpaceae");
        respostas = new ArrayList<>();
        respostas.add(r1);
        respostas.add(r2);
        Pergunta p4 = new Pergunta(4,2, respostas);
        perguntas.add(p4);

        r1 = new Resposta("Folhas isoladas", 6, "");
        r2 = new Resposta("Folhas aos pares ou em feixes", 0, "Pinaceae");
        respostas = new ArrayList<>();
        respostas.add(r1);
        respostas.add(r2);
        Pergunta p5 = new Pergunta(5,2, respostas);
        perguntas.add(p5);

        r1 = new Resposta("Folhas retas", 0, "Taxodiaceae");
        r2 = new Resposta("Folhas curvas", 0, "Araucariaceae");
        respostas = new ArrayList<>();
        respostas.add(r1);
        respostas.add(r2);
        Pergunta p6 = new Pergunta(6,2, respostas);
        perguntas.add(p6);

        r1 = new Resposta("Folhas aciculares", 0, "Araucariaceae");
        r2 = new Resposta("Folhas laminares, oblongas ou lanceoladas", 0, "Cupressaceae");
        r3 = new Resposta("Folhas eliticas", 8, "");
        Resposta r4 = new Resposta("Folhas reduzidas a escamas", 9, "");
        respostas = new ArrayList<>();
        respostas.add(r1);
        respostas.add(r2);
        respostas.add(r3);
        respostas.add(r4);
        Pergunta p7 = new Pergunta(7,2, respostas);
        perguntas.add(p7);

        r1 = new Resposta("Folhas paralelinerveas ou curvinerveas", 0, "Araucariaceae");
        r2 = new Resposta("Folhas peninerveas", 0, "Gnetaceae");
        respostas = new ArrayList<>();
        respostas.add(r1);
        respostas.add(r2);
        Pergunta p8 = new Pergunta(8,2, respostas);
        perguntas.add(p8);

        r1 = new Resposta("Folhas maiores do que os entrenós", 0, "Cupressaceae");
        r2 = new Resposta("Folhas menores do que os entrenós", 10,"");
        respostas = new ArrayList<>();
        respostas.add(r1);
        respostas.add(r2);
        Pergunta p9 = new Pergunta(9,2, respostas);
        perguntas.add(p9);

        r1 = new Resposta("Folhas decurrentes", 0, "Cupressaceae");
        r2 = new Resposta("Folhas não decurrentes", 0, "Ephedraceae");
        respostas = new ArrayList<>();
        respostas.add(r1);
        respostas.add(r2);
        Pergunta p10 = new Pergunta(10,2, respostas);
        perguntas.add(p10);

    }

    private Pergunta obterPerguntaPorId(int id){

        Pergunta p = null;

        for(int i = 0; i < perguntas.size(); i++){
            if(perguntas.get(i).getId() == id){
                p = perguntas.get(i);
            }
        }

        return p;
    }
}
