package com.example.trabalhoddm;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewHolder {

    public ImageView imagemItem;
    public TextView descricaoTransacao;
    public TextView categoriaTransacao;
    public TextView subcategoriaTransacao;
    public TextView valorTransacao;
    public TextView dataTransacao;

    public ListViewHolder(View view) {
        imagemItem = view.findViewById(R.id.imageViewImagemItem);
        descricaoTransacao = view.findViewById(R.id.textViewDescricaoTransacao);
        categoriaTransacao = view.findViewById(R.id.textViewCategoria);
        subcategoriaTransacao = view.findViewById(R.id.textViewSubcategoria);
        valorTransacao = view.findViewById(R.id.textViewValorTransacao);
        dataTransacao = view.findViewById(R.id.textViewData);
    }
}
