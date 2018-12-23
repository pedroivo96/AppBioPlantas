package com.example.pedro.appbioplantas.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.pedro.appbioplantas.R;

public class MenuUsuarioActivity extends AppCompatActivity {

    private ImageButton bChave;
    private ImageButton bCodigo;
    private ImageButton bGuia;
    private ImageButton bFeedback;
    private ImageButton bPerfil;
    private ImageButton bGlossario;
    private ImageButton bListaChaves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);

        bChave = findViewById(R.id.bChave);
        bGuia = findViewById(R.id.bGuia);
        bGlossario = findViewById(R.id.bGlossario);
        bListaChaves = findViewById(R.id.bListaChaves);

        bCodigo = findViewById(R.id.bCodigo);
        bFeedback = findViewById(R.id.bFeedback);
        bPerfil = findViewById(R.id.bPerfil);

        bChave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MenuTipoChaveActivity.class);
                startActivity(intent);
            }
        });

    }

    private Context getContext(){
        return this;
    }
}
