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
import android.widget.TextView;
import android.widget.Toast;

import com.example.pedro.appbioplantas.Controller.AdapterRespostas;
import com.example.pedro.appbioplantas.Model.Pergunta;
import com.example.pedro.appbioplantas.Model.Resposta;
import com.example.pedro.appbioplantas.R;

import java.util.ArrayList;

public class DecisaoChavesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView lRespostas;
    private TextView tTituloPagina;


    private ArrayList<Pergunta> decisaoChaves;
    private Pergunta chavesAuxiliares;
    private Pergunta atual;

    private boolean entrouNaChave = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decisao_chaves);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        decisaoChaves = new ArrayList<>();
        lRespostas = findViewById(R.id.lRespostas);
        tTituloPagina = findViewById(R.id.tTituloPagina);

        Intent intent = getIntent();
        int opcao = intent.getIntExtra("opcao",-1);

        if(opcao == 1){
            //Mostra as Chaves Primárias
            //inicializarDecisaoChaves();

            atual = decisaoChaves.get(0);

            AdapterRespostas adapterRespostas = new AdapterRespostas(getContext(), atual.getRespostas());
            lRespostas.setAdapter(adapterRespostas);

            lRespostas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Resposta r = atual.getRespostas().get(i);

                    if(r.getProximaPergunta() == -1){
                        //Não temos uma proxima pergunta, mas temos um Resultado
                        Toast.makeText(DecisaoChavesActivity.this, r.getResultado(), Toast.LENGTH_SHORT).show();
                        entrouNaChave = true;

                        tTituloPagina.setText(r.getResultado());
                    }
                    else{
                        atual = obterPerguntaPorId(r.getProximaPergunta());
                        AdapterRespostas adapterRespostas = new AdapterRespostas(getContext(), atual.getRespostas());
                        lRespostas.setAdapter(adapterRespostas);
                    }

                }
            });
        }
        else{
            //Mostra as Chaves Secundárias
            //inicializarChavesAuxiliares();
            AdapterRespostas adapterRespostas = new AdapterRespostas(getContext(), chavesAuxiliares.getRespostas());
            lRespostas.setAdapter(adapterRespostas);

            lRespostas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(DecisaoChavesActivity.this, chavesAuxiliares.getRespostas().get(i).getResultado(), Toast.LENGTH_SHORT).show();
                    entrouNaChave = true;

                    tTituloPagina.setText(chavesAuxiliares.getRespostas().get(i).getResultado());
                }
            });
        }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.decisao_chaves, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    /*
    private void inicializarDecisaoChaves(){

        ArrayList<Resposta> respostas = new ArrayList<>();
        Resposta r1 = new Resposta("Pteridófitas",-1,"Chave 1");
        Resposta r2 = new Resposta("Gimnospermas",-1,"Chave 2");
        Resposta r3 = new Resposta("Monocotiledônea",-1,"Chave 3");
        Resposta r4 = new Resposta("Dicotiledôneas",2,"");
        respostas.add(r1);
        respostas.add(r2);
        respostas.add(r3);
        respostas.add(r4);
        Pergunta p1 = new Pergunta(1,-1, respostas);

        respostas = new ArrayList<>();
        r1 = new Resposta("Arquiclamídea",3,"");
        r2 = new Resposta("Metaclamídea",11,"");
        respostas.add(r1);
        respostas.add(r2);
        Pergunta p2 = new Pergunta(2,-1, respostas);

        respostas = new ArrayList<>();
        r1 = new Resposta("Aclamídeos",-1,"Chave 4");
        r2 = new Resposta("Monoclamídea",4,"");
        r3 = new Resposta("Diclamídea",6,"");
        respostas.add(r1);
        respostas.add(r2);
        respostas.add(r3);
        Pergunta p3 = new Pergunta(3,-1, respostas);

        respostas = new ArrayList<>();
        r1 = new Resposta("Superovariados ",5,"");
        r2 = new Resposta("Inferovariadas ou semi-ínfero",-1,"Chave 7");
        respostas.add(r1);
        respostas.add(r2);
        Pergunta p4 = new Pergunta(4,-1, respostas);

        respostas = new ArrayList<>();
        r1 = new Resposta("Flores unissexuais ",-1,"Chave 5");
        r2 = new Resposta("Flores andróginas",-1,"Chave 6");
        respostas.add(r1);
        respostas.add(r2);
        Pergunta p5 = new Pergunta(5,-1, respostas);

        respostas = new ArrayList<>();
        r1 = new Resposta("Superovariados",7,"");
        r2 = new Resposta("Inferovariadas ou semi-ínfero",-1,"Chave 15");
        respostas.add(r1);
        respostas.add(r2);
        Pergunta p6 = new Pergunta(6,-1, respostas);

        respostas = new ArrayList<>();
        r1 = new Resposta("Oligostêmones",-1,"Chave 8");
        r2 = new Resposta("Isostêmones",8,"");
        r3 = new Resposta("Diplostêmones",-1,"Chave 11");
        r4 = new Resposta("Polistêmone",9,"");
        respostas.add(r1);
        respostas.add(r2);
        respostas.add(r3);
        respostas.add(r4);
        Pergunta p7 = new Pergunta(7,-1, respostas);

        respostas = new ArrayList<>();
        r1 = new Resposta("Folhas simples",-1,"Chave 9");
        r2 = new Resposta("Folhas compostas",-1,"Chave 10");
        respostas.add(r1);
        respostas.add(r2);
        Pergunta p8 = new Pergunta(8,-1, respostas);

        respostas = new ArrayList<>();
        r1 = new Resposta("Folhas simples",10,"");
        r2 = new Resposta("Folhas compostas",-1,"Chave 14");
        respostas.add(r1);
        respostas.add(r2);
        Pergunta p9 = new Pergunta(9,-1, respostas);

        respostas = new ArrayList<>();
        r1 = new Resposta("1, 2, 3 ou mais de 5 sépalas",-1,"Chave 12");
        r2 = new Resposta("4 ou 5 sépalas",-1,"Chave 13");
        respostas.add(r1);
        respostas.add(r2);
        Pergunta p10 = new Pergunta(10,-1, respostas);

        respostas = new ArrayList<>();
        r1 = new Resposta("Superovariados",12,"");
        r2 = new Resposta("Inferovariadas ou semi-ínfero",-1,"Chave 20");
        respostas.add(r1);
        respostas.add(r2);
        Pergunta p11 = new Pergunta(11,-1, respostas);

        respostas = new ArrayList<>();
        r1 = new Resposta("Oligostêmones",-1,"Chave 16");
        r2 = new Resposta("Isostêmones",13,"");
        r3 = new Resposta("Diplo e Polistêmone",-1,"Chave 19");
        respostas.add(r1);
        respostas.add(r2);
        respostas.add(r3);
        Pergunta p12 = new Pergunta(12,-1, respostas);

        respostas = new ArrayList<>();
        r1 = new Resposta("Folhas alternas, rosuladas ou faltam",-1,"Chave 17");
        r2 = new Resposta("Folhas opostas, verticiladas, reduzidas a escamas ou faltam",-1,"Chave 18");
        respostas.add(r1);
        respostas.add(r2);
        Pergunta p13 = new Pergunta(13,-1, respostas);

        decisaoChaves.add(p1);
        decisaoChaves.add(p2);
        decisaoChaves.add(p3);
        decisaoChaves.add(p4);
        decisaoChaves.add(p5);
        decisaoChaves.add(p6);
        decisaoChaves.add(p7);
        decisaoChaves.add(p8);
        decisaoChaves.add(p9);
        decisaoChaves.add(p10);
        decisaoChaves.add(p11);
        decisaoChaves.add(p12);
        decisaoChaves.add(p13);

    }
    */

    /*
    private void inicializarChavesAuxiliares(){

        Resposta r1 = new Resposta("Plantas cujas flores apresentam os órgãos da reprodução atrofiados ou hipertrofiados (Plantas cultivadas)", -1, "Chave 21");
        Resposta r2 = new Resposta("Plantas trepadeiras", -1, "Chave 22");
        Resposta r3 = new Resposta("Plantas latescentes", -1, "Chave 23");
        Resposta r4 = new Resposta("Plantas espinhosas ou aculeadas", -1, "Chave 24");
        Resposta r5 = new Resposta("Flor com espora", -1, "Chave 25");
        Resposta r6 = new Resposta("Plantas de frutos alados", -1, "Chave 26");
        Resposta r7 = new Resposta("Plantas sem folhas", -1, "Chave 27");
        Resposta r8 = new Resposta("Anteras valvulares ou poricidas", -1, "Chave 28");
        ArrayList<Resposta> respostas = new ArrayList<>();
        respostas.add(r1);
        respostas.add(r2);
        respostas.add(r3);
        respostas.add(r4);
        respostas.add(r5);
        respostas.add(r6);
        respostas.add(r7);
        respostas.add(r8);
        chavesAuxiliares = new Pergunta(-1,-1, respostas);
    }
    */

    private Pergunta obterPerguntaPorId(int id){

        Pergunta p = null;

        for(int i = 0; i < decisaoChaves.size(); i++){
            if(decisaoChaves.get(i).getId() == id){
                p = decisaoChaves.get(i);
            }
        }

        return p;
    }
}
