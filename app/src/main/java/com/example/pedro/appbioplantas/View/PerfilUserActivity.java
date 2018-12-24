package com.example.pedro.appbioplantas.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pedro.appbioplantas.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PerfilUserActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private ImageView userFotoImageView;
    private TextView userEmailTextView;
    private TextView userIDTextView;
    private TextView userNameTextView;
    private Button btnSignOut;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser firebaseUser;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_user);

        userFotoImageView = (ImageView) findViewById(R.id.userFoto);
        userEmailTextView = (TextView) findViewById(R.id.userEmail);
        userIDTextView = (TextView) findViewById(R.id.userID);
        userNameTextView = (TextView) findViewById(R.id.userName);
        btnSignOut = (Button) findViewById(R.id.btnSignOut);

        mFirebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null){
                    exibirDadosUser(firebaseUser);
                }else{
                    finish();
                }
            }
        };

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth.signOut();
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        alert("Conta desconectada!");
                        Intent intent = new Intent(getContext(), LoginGoogleActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }

    private void exibirDadosUser(FirebaseUser firebaseUser){
        userNameTextView.setText(firebaseUser.getDisplayName());
        userEmailTextView.setText("Email: "+firebaseUser.getEmail());
        userIDTextView.setText("UID: "+firebaseUser.getUid());
        Glide.with(getContext()).load(firebaseUser.getPhotoUrl()).apply(RequestOptions.circleCropTransform()).into(userFotoImageView);
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

    private Context getContext() {
        return this;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        alert("Falha na conexão!");
    }

    public void alert(String s){
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }
}
