package com.cinereview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FilmeAdapter extends RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder> {
    private Context mContext;
    private ArrayList<Filme> filmesList;

    public FilmeAdapter(Context context, ArrayList<Filme> list) {
        mContext = context;
        filmesList = list;
    }

    @NonNull
    @Override
    public FilmeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.filme_list, parent, false);
        return new FilmeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmeViewHolder holder, int position) {
        Filme currentFilme = filmesList.get(position);

        holder.cartaz.setImageBitmap(currentFilme.getCartaz());
        holder.titulo.setText(currentFilme.getNome());
        // holder.genero.setText(currentFilme.getGenero());
        holder.nota.setText("Nota: " + currentFilme.getNota());
        holder.descricao.setText(currentFilme.getDescricao());
    }

    @Override
    public int getItemCount() {
        return filmesList.size();
    }

    public static class FilmeViewHolder extends RecyclerView.ViewHolder {
        ImageView cartaz;
        TextView titulo;
        TextView genero;
        TextView nota;
        TextView descricao;

        public FilmeViewHolder(@NonNull View itemView) {
            super(itemView);
            cartaz = itemView.findViewById(R.id.image_filme);
            titulo = itemView.findViewById(R.id.text_titulo);
            // genero = itemView.findViewById(R.id.text_genero);
            nota = itemView.findViewById(R.id.text_nota);
            descricao = itemView.findViewById(R.id.text_descricao);
        }
    }
}
