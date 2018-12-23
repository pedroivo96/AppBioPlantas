package com.example.pedro.appbioplantas.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.pedro.appbioplantas.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class PlantasBD extends SQLiteOpenHelper {

    private SharedPreferences sharedPref;

    private static final String NOME_BANCO = "plantas.db";
    private static final int VERSA0_BANCO = 1;

    public PlantasBD(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSA0_BANCO);

        sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file), Context.MODE_PRIVATE);
        //verifica se ja ta cadastrado
        if(! sharedPref.contains(context.getString(R.string.saved_state))){
            importarBD(context);
            String state = "BD_Importado";
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(context.getString(R.string.saved_state), state);
            editor.commit();

            Toast.makeText(context, "Primeiro uso!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    public void importarBD(Context context) {
        try {
            AssetManager assests = context.getAssets();
            InputStream fis = assests.open("plantas.db");

            File diretorio = new File("/data/data/com.example.pedro.appbioplantas/databases");
            File arqSaida = new File("/data/data/com.example.pedro.appbioplantas/databases/"+NOME_BANCO);

            if(! diretorio.exists()){
                diretorio.mkdir();
                arqSaida.createNewFile();
                arqSaida.createNewFile();
            }

            OutputStream saida = new FileOutputStream(arqSaida);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer))>0){
                saida.write(buffer, 0, length);
            }
            saida.flush();
            saida.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
