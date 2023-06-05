package com.cinereview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.squareup.picasso.Picasso;

public class DiretorAdapter extends ArrayAdapter<Diretor> {

    private Context mContext;
    private List<Diretor> diretorList;

    public DiretorAdapter(Context context, List<Diretor> list) {
        super(context, 0, list);
        mContext = context;
        diretorList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item_celebridade, parent, false);

        Diretor currentDiretor = diretorList.get(position);

//        ImageView foto = listItem.findViewById(R.id.image_foto);
//        Picasso.get().load(currentDiretor.getFotoUrl()).into(foto);

        TextView nome = listItem.findViewById(R.id.text_nome);
        nome.setText(currentDiretor.getNome());

        TextView idade = listItem.findViewById(R.id.text_idade);
        idade.setText(String.valueOf(currentDiretor.getIdade()));

        TextView nacionalidade = listItem.findViewById(R.id.text_nacionalidade);
        nacionalidade.setText(currentDiretor.getNacionalidade());

        return listItem;
    }
}
