package com.example.pedro.appbioplantas.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pedro.appbioplantas.Model.Resposta;
import com.example.pedro.appbioplantas.R;

import java.util.ArrayList;

public class AdapterRespostasEscolhidas extends BaseAdapter {

    private Context context;
    private ArrayList<Resposta> respostasEscolhidas;

    public AdapterRespostasEscolhidas(Context context, ArrayList<Resposta> respostasEscolhidas){
        this.context = context;
        this.respostasEscolhidas = respostasEscolhidas;
    }

    @Override
    public int getCount() {
        return respostasEscolhidas.size();
    }

    @Override
    public Object getItem(int i) {
        return respostasEscolhidas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View view1 = LayoutInflater.from(context).inflate(R.layout.item_listview_respostas_escolhidas, viewGroup, false);

        Resposta r = respostasEscolhidas.get(i);

        TextView tRespostaEscolhida = view1.findViewById(R.id.respostaEscolhida);
        tRespostaEscolhida.setText(r.getEnunciado());

        return view1;
    }
}
