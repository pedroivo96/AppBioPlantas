package com.example.pedro.appbioplantas.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pedro.appbioplantas.R;

public class MenuTipoChaveActivity extends AppCompatActivity {

    private Button bChavesPrimarias;
    private Button bChavesSecundarias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tipo_chave);

        bChavesPrimarias = findViewById(R.id.bChavesPrimarias);
        bChavesSecundarias = findViewById(R.id.bChavesSecundarias);

        bChavesPrimarias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChavesActivity.class);
                intent.putExtra("tipoChave",1);
                startActivity(intent);
            }
        });

        bChavesSecundarias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChavesActivity.class);
                intent.putExtra("tipoChave",2);
                startActivity(intent);
            }
        });
    }

    private Context getContext(){
        return this;
    }
}
