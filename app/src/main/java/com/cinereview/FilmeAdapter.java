package com.cinereview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cinereview.Filme;
import com.cinereview.R;

import java.util.ArrayList;
import java.util.List;

public class FilmeAdapter extends RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder> {
    private Context mContext;
    private List<Filme> filmesList;

    public FilmeAdapter(Context context, ArrayList<Filme> list) {
        mContext = context;
        filmesList = list;
    }

    @Override
    public FilmeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list, parent, false);
        return new FilmeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FilmeViewHolder holder, int position) {
        Filme currentFilme = filmesList.get(position);

        //holder.cartaz.setImageBitmap(currentFilme.getCartaz());
        holder.titulo.setText(currentFilme.getNome());
        holder.genero.setText(String.join(", ", currentFilme.getGenero()));
        holder.nota.setText("Nota: " + currentFilme.getNota());
    }

    @Override
    public int getItemCount() {
        return filmesList.size();
    }

    public class FilmeViewHolder extends RecyclerView.ViewHolder {
        ImageView cartaz;
        TextView titulo;
        TextView genero;
        TextView nota;

        public FilmeViewHolder(View itemView) {
            super(itemView);

            cartaz = itemView.findViewById(R.id.image_filme);
            titulo = itemView.findViewById(R.id.text_titulo);
            genero = itemView.findViewById(R.id.text_genero);
            nota = itemView.findViewById(R.id.text_nota);
        }
    }
}
