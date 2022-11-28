package com.example.trabalhoddm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class TransactionsListAdapter extends ArrayAdapter<String> {

    Context context;
    private ArrayList<Integer> imagensLista = new ArrayList<>();
    private ArrayList<String> descricaoTransacao = new ArrayList<>();
    private ArrayList<String> categoriaTransacao = new ArrayList<>();
    private ArrayList<String> subcategoriaTransacao = new ArrayList<>();
    private ArrayList<String> valorTransacao = new ArrayList<>();
    private ArrayList<String> dataTransacao = new ArrayList<>();

    public TransactionsListAdapter(Context context, ArrayList<Transacao> listaTrasacoesFiltradas, String[] quantidade) {
        super(context, R.layout.single_item, R.id.textViewDescricaoTransacao, quantidade);
        this.context = context;

        for (Transacao transacao: listaTrasacoesFiltradas) {
            descricaoTransacao.add(transacao.getDescricao());
            categoriaTransacao.add(transacao.getCategoria().getNome());
            subcategoriaTransacao.add(transacao.getSubcategoria().getNome());
            valorTransacao.add("R$ "+String.valueOf(transacao.getValor()));
            dataTransacao.add(transacao.getData());
            if(transacao.getTipo().equals("Receber")){
                imagensLista.add(R.drawable.ic_baseline_attach_money_24_green);
            }
            if(transacao.getTipo().equals("Pagar")){
                imagensLista.add(R.drawable.ic_baseline_attach_money_24);
            }
            if(transacao.getTipo().equals("Transferencia")){
                imagensLista.add(R.drawable.ic_currency_exchange);
            }
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View singleItem = convertView;
        ListViewHolder viewHolder = null;
        if(singleItem == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem = layoutInflater.inflate(R.layout.single_item, parent, false);
            viewHolder = new ListViewHolder(singleItem);
            singleItem.setTag(viewHolder);
        }
        else{
            viewHolder = (ListViewHolder) singleItem.getTag();
        }

        viewHolder.imagemItem.setImageResource(imagensLista.get(position));
        viewHolder.descricaoTransacao.setText(descricaoTransacao.get(position));
        viewHolder.categoriaTransacao.setText(categoriaTransacao.get(position));
        viewHolder.subcategoriaTransacao.setText(subcategoriaTransacao.get(position));
        viewHolder.valorTransacao.setText(valorTransacao.get(position));
        viewHolder.dataTransacao.setText(dataTransacao.get(position));

        return singleItem;
    }

    public void atualizarLista(){
        this.notifyDataSetChanged();
    }

}
