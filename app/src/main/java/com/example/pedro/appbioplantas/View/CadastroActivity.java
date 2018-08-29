package com.example.pedro.appbioplantas.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.pedro.appbioplantas.R;

public class CadastroActivity extends AppCompatActivity {

    private ImageButton bFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        bFinalizar = findViewById(R.id.bFinalizar);

        bFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MenuUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }

    private Context getContext(){
        return this;
    }
}
