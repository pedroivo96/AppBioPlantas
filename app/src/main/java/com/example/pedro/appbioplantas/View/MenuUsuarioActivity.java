package com.example.pedro.appbioplantas.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
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
        bCodigo = findViewById(R.id.bCodigo);
        bGuia = findViewById(R.id.bGuia);
        bFeedback = findViewById(R.id.bFeedback);
        bPerfil = findViewById(R.id.bPerfil);
        bGlossario = findViewById(R.id.bGlossario);
        bListaChaves = findViewById(R.id.bListaChaves);
    }
}
