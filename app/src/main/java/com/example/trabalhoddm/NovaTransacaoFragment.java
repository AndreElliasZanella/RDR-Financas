package com.example.trabalhoddm;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NovaTransacaoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NovaTransacaoFragment extends Fragment {

    Context context;

    private TextInputEditText txtDescricao;
    private TextInputEditText txtValor;

    private ImageButton btnConfirmarCadastro;
    private ImageButton btnVoltar;

    private Spinner SpinnerTipoTransacoes;
    private Spinner SpinnerCategorias;
    private Spinner SpinnerSubcategorias;
    private Spinner SpinnerBancos;
    private Spinner SpinnerContasBancarias;

    private DatePickerDialog datePickerDialog;
    private Button datePickerButton;

    private String[] TipoTransacoes = {"Receber","Pagar"};
    private ArrayList<String> categoriasNome = new ArrayList<>();
    private ArrayList<String> subcategorias = new ArrayList<>();;
    private ArrayList<String> bancos = new ArrayList<>();;
    private ArrayList<String> contasBancarias = new ArrayList<>();;

    public NovaTransacaoFragment() {
        // Required empty public constructor
    }

    public static NovaTransacaoFragment newInstance(String param1, String param2) {
        NovaTransacaoFragment fragment = new NovaTransacaoFragment();
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
        return inflater.inflate(R.layout.fragment_nova_transacao, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        context = this.getContext();
        initDatePicker();

        txtDescricao = (TextInputEditText) getView().findViewById(R.id.txtDescricao);
        txtValor = (TextInputEditText) getView().findViewById(R.id.txtValor);

        datePickerButton = (Button) getView().findViewById(R.id.DatePicker);
        datePickerButton.setText(getDataDeHoje());

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker(getView());
            }
        });

        btnConfirmarCadastro = (ImageButton) getView().findViewById(R.id.ImgBtnConfirmarTransacao);
        btnConfirmarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Categoria categoriaTransacao = null;
                SubCategoria subCategoriaTransacao = null;
                Banco bancoTransacao = null;
                ContaBancaria contaTransacao = null;

                for (SubCategoria subcategoria: MainActivity.subcategorias) {
                    if(subcategoria.getNome().equals(SpinnerSubcategorias.getSelectedItem().toString()) && subcategoria.getCategoria().getNome().equals(SpinnerCategorias.getSelectedItem().toString())){
                        categoriaTransacao = subcategoria.getCategoria();
                        subCategoriaTransacao = subcategoria;
                    }
                }

                for (ContaBancaria conta: MainActivity.contasBancarias) {
                    if(conta.getNome().equals(SpinnerContasBancarias.getSelectedItem().toString()) && conta.getBanco().getNome().equals(SpinnerBancos.getSelectedItem().toString())){
                        bancoTransacao = conta.getBanco();
                        contaTransacao = conta;
                    }
                }

                Transacao novaTransacao = new Transacao(
                        SpinnerTipoTransacoes.getSelectedItem().toString(),
                        txtDescricao.getText().toString(),
                        datePickerButton.getText().toString(),
                        Double.parseDouble(txtValor.getText().toString()),
                        categoriaTransacao,
                        subCategoriaTransacao,
                        bancoTransacao,
                        contaTransacao
                );

                MainActivity.transacoes.add(novaTransacao);

                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView6, TransacoesMenuFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
            }
        });

        btnVoltar = (ImageButton) getView().findViewById(R.id.ImgBtnVoltarNovaTransacao);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView6, TransacoesMenuFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
            }
        });

        for (Categoria categoria: MainActivity.categorias) {
            categoriasNome.add(categoria.getNome());
        }

        atualizarSubcategorias(MainActivity.categorias.get(0));

        for (Banco banco: MainActivity.bancos) {
            bancos.add(banco.getNome());
        }

        atualizarContasBancarias(MainActivity.bancos.get(0));

        //Spinner de Transações
        SpinnerTipoTransacoes = (Spinner) getView().findViewById(R.id.SpinnerTipoTransacao);
        ArrayAdapter transacaoAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_dropdown_item, TipoTransacoes);
        SpinnerTipoTransacoes.setAdapter(transacaoAdapter);

        //Spinner de Categorias
        SpinnerCategorias = (Spinner) getView().findViewById(R.id.SpinnerCategoria);
        ArrayAdapter categoriaAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_dropdown_item, categoriasNome);
        SpinnerCategorias.setAdapter(categoriaAdapter);
        SpinnerCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                atualizarSubcategorias(MainActivity.categorias.get(SpinnerCategorias.getSelectedItemPosition()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Spinner de Subcategorias
        SpinnerSubcategorias = (Spinner) getView().findViewById(R.id.SpinnerSubcategoria);
        ArrayAdapter subcategoriaAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_dropdown_item, subcategorias);
        SpinnerSubcategorias.setAdapter(subcategoriaAdapter);

        //Spinner de Bancos
        SpinnerBancos = (Spinner) getView().findViewById(R.id.SpinnerBanco);
        ArrayAdapter bancoAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_dropdown_item, bancos);
        SpinnerBancos.setAdapter(bancoAdapter);
        SpinnerBancos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                atualizarContasBancarias(MainActivity.bancos.get(SpinnerBancos.getSelectedItemPosition()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Spinner de Contas Bancarias
        SpinnerContasBancarias = (Spinner) getView().findViewById(R.id.SpinnerContaBancaria);
        ArrayAdapter contasBancariasAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_dropdown_item, contasBancarias);
        SpinnerContasBancarias.setAdapter(contasBancariasAdapter);

    }

    public void atualizarSubcategorias(Categoria categoriaSelecionada){
        subcategorias.clear();
        for (SubCategoria subcategoria : MainActivity.subcategorias) {
            if(subcategoria.getCategoria() == categoriaSelecionada){
                subcategorias.add(subcategoria.getNome());
            }
        }
    }

    public void atualizarContasBancarias(Banco bancoSelecionado){
        contasBancarias.clear();
        for (ContaBancaria conta : MainActivity.contasBancarias) {
            if(conta.getBanco() == bancoSelecionado){
                contasBancarias.add(conta.getNome());
            }
        }
    }


    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                mes+=1;
                String data = makeDataString(ano, mes, dia);
                datePickerButton.setText(data);
            }
        };

        Calendar calendario = Calendar.getInstance();
        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(context, style, dateSetListener, ano, mes, dia);
    }

    private String makeDataString(int ano, int mes, int dia) {
        return dia + "/" + mes + "/" + ano;
    }

    public void openDatePicker(View view){
        datePickerDialog.show();
    }

    public String getDataDeHoje(){
        Calendar calendario = Calendar.getInstance();
        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        mes+=1;
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        return makeDataString(ano, mes, dia);
    }
}