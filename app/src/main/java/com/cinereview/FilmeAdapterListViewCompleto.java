package com.cinereview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FilmeAdapterListViewCompleto extends FilmeAdapterListView {

    public FilmeAdapterListViewCompleto(Context context, ArrayList<Filme> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.my_list_completo, parent, false);

        Filme currentFilme = filmesList.get(position);

        ImageView cartaz = listItem.findViewById(R.id.image_filme);
        Picasso.get().load(currentFilme.getCartaz()).into(cartaz);

        TextView descricao = listItem.findViewById(R.id.text_descricao);
        descricao.setText(currentFilme.getDescricao());

        TextView titulo = listItem.findViewById(R.id.text_titulo);
        titulo.setText(currentFilme.getNome());

        TextView genero = listItem.findViewById(R.id.text_genero);
        String generoString = TextUtils.join(", ", currentFilme.getGenero());
        genero.setText(generoString);

        TextView atores = listItem.findViewById(R.id.text_atores);
        Ator auxAtores[] = currentFilme.getAtores();
        String atoresString = "";
        for (int i = 0; i < auxAtores.length; i++) {
            atoresString += auxAtores[i].getNome();
            if (i < auxAtores.length - 1) {
                atoresString += ", ";
            }
        }

        atores.setText(atoresString);

        TextView diretor = listItem.findViewById(R.id.text_diretor);
        Diretor aux = currentFilme.getDiretor();
        diretor.setText(aux.getNome());

        TextView nota = listItem.findViewById(R.id.text_nota);
        nota.setText("Nota: " + currentFilme.getNota());



        return listItem;
    }
}