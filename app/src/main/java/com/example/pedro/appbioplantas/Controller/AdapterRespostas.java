package com.example.pedro.appbioplantas.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pedro.appbioplantas.Model.Resposta;
import com.example.pedro.appbioplantas.R;

import java.util.ArrayList;

public class AdapterRespostas extends BaseAdapter {

    private Context context;
    private ArrayList<Resposta> respostas;

    public AdapterRespostas(Context context, ArrayList<Resposta> respostas){
        this.context = context;
        this.respostas = respostas;
    }

    @Override
    public int getCount() {
        return respostas.size();
    }

    @Override
    public Object getItem(int i) {
        return respostas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View view1 = LayoutInflater.from(context).inflate(R.layout.item_listview_respostas, viewGroup, false);

        Resposta r = respostas.get(i);

        TextView tResposta = view1.findViewById(R.id.resposta);
        tResposta.setText(r.getEnunciado());

        return view1;
    }
}
