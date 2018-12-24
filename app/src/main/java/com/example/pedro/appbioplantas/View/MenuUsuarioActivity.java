package com.example.pedro.appbioplantas.View;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.pedro.appbioplantas.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuUsuarioActivity extends AppCompatActivity {

    private ImageButton bChave;
    private ImageButton bCodigo;
    private ImageButton bGuia;
    private ImageButton bFeedback;
    private ImageButton bPerfil;
    private ImageButton bGlossario;
    private ImageButton bListaChaves;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);

        mFirebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) { //verifica se está logado
                firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser == null){
                    finish();
                }
            }
        };

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

        bPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PerfilUserActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFirebaseAuth.removeAuthStateListener(authStateListener);
    }

    private Context getContext(){
        return this;
    }
}
