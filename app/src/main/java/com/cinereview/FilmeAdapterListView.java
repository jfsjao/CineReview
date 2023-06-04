package com.cinereview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.squareup.picasso.Picasso;
public class FilmeAdapterListView extends ArrayAdapter<Filme> {
    private Context mContext;
    private List<Filme> filmesList;

    public FilmeAdapterListView(Context context, ArrayList<Filme> list) {
        super(context, 0, list);
        mContext = context;
        filmesList = list;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.my_list, parent, false);

        Filme currentFilme = filmesList.get(position);

        ImageView cartaz = listItem.findViewById(R.id.image_filme);
        Picasso.get().load(currentFilme.getCartaz()).into(cartaz);


        // TextView genero = listItem.findViewById(R.id.text_genero);
        // genero.setText(currentFilme.getGenero());

        TextView nota = listItem.findViewById(R.id.text_nota);
        nota.setText("Nota: " + currentFilme.getNota());


        return listItem;
    }
}
