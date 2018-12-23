package com.example.pedro.appbioplantas.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pedro.appbioplantas.Model.Chave;
import com.example.pedro.appbioplantas.Model.Resposta;
import com.example.pedro.appbioplantas.R;

import java.util.ArrayList;

public class AdapterChaves extends BaseAdapter {

    private Context context;
    private ArrayList<Chave> chaves;

    public AdapterChaves(Context context, ArrayList<Chave> chaves){
        this.context = context;
        this.chaves = chaves;
    }

    @Override
    public int getCount() {
        return chaves.size();
    }

    @Override
    public Object getItem(int i) {
        return chaves.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View view1 = LayoutInflater.from(context).inflate(R.layout.item_listview_chaves, viewGroup, false);

        Chave c = chaves.get(i);

        TextView tResposta = view1.findViewById(R.id.resposta);
        tResposta.setText(c.getNome());

        return view1;
    }
}
