package com.example.trabalhoddm;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransacoesMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransacoesMenuFragment extends Fragment {

    private int numeroMes;

    private TextView valorReceitas;
    private TextView valorDespesas;
    private TextView valorTransferencias;
    private TextView nomeMes;

    private ListView listaTransacoes;

    private ImageButton verTransacoesReceber;
    private ImageButton verTransacoesDespesas;
    private ImageButton verTransacoesTransferencias;
    private ImageButton addTransacao;
    private ImageButton proxMes;
    private ImageButton anteMes;

    private String tipoListaAtual;

    private double valorSomaReceitas = 0;
    private double valorSomaDespesas = 0;
    private double valorSomaTransferencias = 0;

    private ArrayList<Transacao> listaTrasacoesFiltradas = new ArrayList<>();
    private String[] listaEmString;

    public TransacoesMenuFragment() {
        // Required empty public constructor
    }

    public static TransacoesMenuFragment newInstance(String param1, String param2) {
        TransacoesMenuFragment fragment = new TransacoesMenuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transacoes_menu, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        Context context = this.getContext();

        nomeMes = (TextView) getView().findViewById(R.id.textViewNomeMes);
        Calendar calendario = Calendar.getInstance();
        numeroMes = calendario.get(Calendar.MONTH);
        numeroMes++;
        nomeMes.setText(pegarMes(numeroMes));

        tipoListaAtual = "Receber";
        for (Transacao transacao : MainActivity.transacoes) {
            if(transacao.getTipo().equals("Receber")){
                if(transacao.getData().split("/")[1].equals(String.valueOf(numeroMes))){
                    listaTrasacoesFiltradas.add(transacao);
                    valorSomaReceitas+=transacao.getValor();
                }
            }
            if(transacao.getTipo().equals("Pagar")){
                if(transacao.getData().split("/")[1].equals(String.valueOf(numeroMes))){
                    valorSomaDespesas+=transacao.getValor();
                }
            }
            if(transacao.getTipo().equals("Transferencia")){
                if(transacao.getData().split("/")[1].equals(String.valueOf(numeroMes))){
                    valorSomaTransferencias+=transacao.getValor();
                }
            }
        }

        valorReceitas = (TextView) getView().findViewById(R.id.TextViewResumoReceitas);
        valorDespesas = (TextView) getView().findViewById(R.id.TextViewResumoDespesas);
        valorTransferencias= (TextView) getView().findViewById(R.id.TextViewResumoTransferencias);

        listaEmString = new String[listaTrasacoesFiltradas.size()];
        for (int i = 0; i<listaTrasacoesFiltradas.size(); i++) {
            listaEmString[i] = listaTrasacoesFiltradas.get(i).getDescricao();
        }

        listaTransacoes = (ListView) getView().findViewById(R.id.ListviewTransacoes);
        TransactionsListAdapter listAdapter = new TransactionsListAdapter(context, listaTrasacoesFiltradas, listaEmString);
        listaTransacoes.setAdapter(listAdapter);

        anteMes = (ImageButton) getView().findViewById(R.id.imageButtonAnteMes) ;
        anteMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numeroMes--;
                if(numeroMes < 1){
                    numeroMes = 12;
                }
                nomeMes.setText(pegarMes(numeroMes));

                valorSomaReceitas = 0;
                valorSomaTransferencias = 0;
                valorSomaDespesas = 0;

                listaTrasacoesFiltradas.clear();

                for (Transacao transacao : MainActivity.transacoes) {
                    if(transacao.getTipo().equals(tipoListaAtual)){
                        if(transacao.getData().split("/")[1].equals(String.valueOf(numeroMes))){
                            listaTrasacoesFiltradas.add(transacao);
                        }
                    }
                    if(transacao.getData().split("/")[1].equals(String.valueOf(numeroMes))){
                        if (transacao.getTipo().equals("Receber")){
                            valorSomaReceitas += transacao.getValor();
                        }
                        if (transacao.getTipo().equals("Pagar")){
                            valorSomaDespesas += transacao.getValor();
                        }
                        if (transacao.getTipo().equals("Transferencia")){
                            valorSomaTransferencias += transacao.getValor();
                        }
                    }
                }

                listaEmString = new String[listaTrasacoesFiltradas.size()];

                for (int i = 0; i<listaTrasacoesFiltradas.size(); i++) {
                    listaEmString[i] = listaTrasacoesFiltradas.get(i).getDescricao();
                }

                TransactionsListAdapter listAdapter = new TransactionsListAdapter(context, listaTrasacoesFiltradas, listaEmString);
                listaTransacoes.setAdapter(listAdapter);

                valorReceitas.setText("R$ "+String.valueOf(valorSomaReceitas));
                valorDespesas.setText("R$ "+String.valueOf(valorSomaDespesas));
                valorTransferencias.setText("R$ "+String.valueOf(valorSomaTransferencias));
            }
        });

        proxMes = (ImageButton) getView().findViewById(R.id.imageButtonProxMes);
        proxMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numeroMes++;
                if(numeroMes > 12){
                    numeroMes = 1;
                }
                nomeMes.setText(pegarMes(numeroMes));

                valorSomaReceitas = 0;
                valorSomaTransferencias = 0;
                valorSomaDespesas = 0;

                listaTrasacoesFiltradas.clear();

                for (Transacao transacao : MainActivity.transacoes) {
                    if(transacao.getTipo().equals(tipoListaAtual)){
                        if(transacao.getData().split("/")[1].equals(String.valueOf(numeroMes))){
                            listaTrasacoesFiltradas.add(transacao);
                        }
                    }
                    if(transacao.getData().split("/")[1].equals(String.valueOf(numeroMes))){
                        if (transacao.getTipo().equals("Receber")){
                            valorSomaReceitas += transacao.getValor();
                        }
                        if (transacao.getTipo().equals("Pagar")){
                            valorSomaDespesas += transacao.getValor();
                        }
                        if (transacao.getTipo().equals("Transferencia")){
                            valorSomaTransferencias += transacao.getValor();
                        }
                    }
                }

                listaEmString = new String[listaTrasacoesFiltradas.size()];

                for (int i = 0; i<listaTrasacoesFiltradas.size(); i++) {
                    listaEmString[i] = listaTrasacoesFiltradas.get(i).getDescricao();
                }

                TransactionsListAdapter listAdapter = new TransactionsListAdapter(context, listaTrasacoesFiltradas, listaEmString);
                listaTransacoes.setAdapter(listAdapter);

                valorReceitas.setText("R$ "+String.valueOf(valorSomaReceitas));
                valorDespesas.setText("R$ "+String.valueOf(valorSomaDespesas));
                valorTransferencias.setText("R$ "+String.valueOf(valorSomaTransferencias));

                }
        });

        addTransacao = (ImageButton) getView().findViewById(R.id.imageButtonAddTransacao);
        addTransacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView6, NovaTransacaoFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
            }
        });

        verTransacoesReceber = (ImageButton) getView().findViewById(R.id.BTNViewReceitas);
        verTransacoesDespesas = (ImageButton) getView().findViewById(R.id.BTNViewDespesas);
        verTransacoesTransferencias = (ImageButton) getView().findViewById(R.id.BTNViewTransferencias);

        verTransacoesDespesas.getDrawable().setTint(getResources().getColor(R.color.cinza));
        verTransacoesTransferencias.getDrawable().setTint(getResources().getColor(R.color.cinza));

        valorReceitas.setText("R$ "+String.valueOf(valorSomaReceitas));
        valorDespesas.setText("R$ "+String.valueOf(valorSomaDespesas));
        valorTransferencias.setText("R$ "+String.valueOf(valorSomaTransferencias));

        verTransacoesReceber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipoListaAtual = "Receber";
                listaTrasacoesFiltradas.clear();
                for (Transacao transacao : MainActivity.transacoes) {
                    if(transacao.getTipo().equals("Receber")){
                        if(transacao.getData().split("/")[1].equals(String.valueOf(numeroMes))){
                            listaTrasacoesFiltradas.add(transacao);
                        }
                    }
                }
                listaEmString = new String[listaTrasacoesFiltradas.size()];
                for (int i = 0; i<listaTrasacoesFiltradas.size(); i++) {
                    listaEmString[i] = listaTrasacoesFiltradas.get(i).getDescricao();
                }
                TransactionsListAdapter listAdapterReceber = new TransactionsListAdapter(context, listaTrasacoesFiltradas, listaEmString);
                listaTransacoes.setAdapter(listAdapterReceber);

                verTransacoesReceber.getDrawable().setTint(getResources().getColor(R.color.verde));
                verTransacoesDespesas.getDrawable().setTint(getResources().getColor(R.color.cinza));
                verTransacoesTransferencias.getDrawable().setTint(getResources().getColor(R.color.cinza));
            }
        });

        verTransacoesDespesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipoListaAtual = "Pagar";
                listaTrasacoesFiltradas.clear();
                for (Transacao transacao : MainActivity.transacoes) {
                    if(transacao.getTipo().equals("Pagar")){
                        if(transacao.getData().split("/")[1].equals(String.valueOf(numeroMes))){
                            listaTrasacoesFiltradas.add(transacao);
                        }
                    }
                }
                listaEmString = new String[listaTrasacoesFiltradas.size()];
                for (int i = 0; i<listaTrasacoesFiltradas.size(); i++) {
                    listaEmString[i] = listaTrasacoesFiltradas.get(i).getDescricao();
                }
                TransactionsListAdapter listAdapterDespesas = new TransactionsListAdapter(context, listaTrasacoesFiltradas, listaEmString);
                listaTransacoes.setAdapter(listAdapterDespesas);

                verTransacoesReceber.getDrawable().setTint(getResources().getColor(R.color.cinza));
                verTransacoesDespesas.getDrawable().setTint(getResources().getColor(R.color.vermelho));
                verTransacoesTransferencias.getDrawable().setTint(getResources().getColor(R.color.cinza));
            }
        });

        verTransacoesTransferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipoListaAtual = "Transferencia";
                listaTrasacoesFiltradas.clear();
                for (Transacao transacao : MainActivity.transacoes) {
                    if(transacao.getTipo().equals("Transferencia")){
                        if(transacao.getData().split("/")[1].equals(String.valueOf(numeroMes))){
                            listaTrasacoesFiltradas.add(transacao);
                        }
                    }
                }
                listaEmString = new String[listaTrasacoesFiltradas.size()];
                for (int i = 0; i<listaTrasacoesFiltradas.size(); i++) {
                    listaEmString[i] = listaTrasacoesFiltradas.get(i).getDescricao();
                }
                TransactionsListAdapter listAdapterTransferencias = new TransactionsListAdapter(context, listaTrasacoesFiltradas, listaEmString);
                listaTransacoes.setAdapter(listAdapterTransferencias);

                verTransacoesReceber.getDrawable().setTint(getResources().getColor(R.color.cinza));
                verTransacoesDespesas.getDrawable().setTint(getResources().getColor(R.color.cinza));
                verTransacoesTransferencias.getDrawable().setTint(getResources().getColor(R.color.azul));
            }
        });

    }

    public String pegarMes(int numeroMes){
        if (numeroMes == 1){
            return "Janeiro";
        }
        if (numeroMes == 2){
            return "Fevereiro";
        }
        if (numeroMes == 3){
            return "Março";
        }
        if (numeroMes == 4){
            return "Abril";
        }
        if (numeroMes == 5){
            return "Maio";
        }
        if (numeroMes == 6){
            return "Junho";
        }
        if (numeroMes == 7){
            return "Julho";
        }
        if (numeroMes == 8){
            return "Agosto";
        }
        if (numeroMes == 9){
            return "Setembro";
        }
        if (numeroMes == 10){
            return "Outubro";
        }
        if (numeroMes == 11){
            return "Novembro";
        }
        if (numeroMes == 12){
            return "Dezembro";
        }

        return "Janeiro";
    }
}