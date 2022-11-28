package com.example.trabalhoddm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton BtnMenuLateral;
    public static ArrayList<Categoria> categorias = new ArrayList();
    public static ArrayList<SubCategoria> subcategorias = new ArrayList();
    public static ArrayList<Banco> bancos = new ArrayList();
    public static ArrayList<ContaBancaria> contasBancarias = new ArrayList();
    public static ArrayList<Transacao> transacoes = new ArrayList<>();

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDados();

        fragmentManager = getSupportFragmentManager();

        BtnMenuLateral = (ImageButton) findViewById(R.id.BtnMenuLateral);
        BtnMenuLateral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView6, NovaTransacaoFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
            }
        });
    }

    private void getDados(){
        Categoria categoria1 = new Categoria("Casa");
        Categoria categoria2 = new Categoria("Comida");
        Categoria categoria3 = new Categoria("Laser");

        SubCategoria subcategoria1 = new SubCategoria("agua", categoria1);
        SubCategoria subcategoria2 = new SubCategoria("luz", categoria1);
        SubCategoria subcategoria3 = new SubCategoria("mercado", categoria2);
        SubCategoria subcategoria4 = new SubCategoria("restaurante", categoria2);
        SubCategoria subcategoria5 = new SubCategoria("jogos", categoria3);
        SubCategoria subcategoria6 = new SubCategoria("roupa", categoria3);
        SubCategoria subcategoria7 = new SubCategoria("anime", categoria3);

        Banco banco1 = new Banco("Banco do Brasil", "001");
        Banco banco2 = new Banco("Bradesco", "002");

        ContaBancaria contaBancaria1 = new ContaBancaria("Conta Corrente 1", "Corrente", banco1);
        ContaBancaria contaBancaria2 = new ContaBancaria("Conta Poupança 1", "Poupança", banco1);
        ContaBancaria contaBancaria3 = new ContaBancaria("Conta Corrente 2", "Corrente", banco2);
        ContaBancaria contaBancaria4 = new ContaBancaria("Conta Poupança 2", "Poupança", banco2);

        categorias.add(categoria1);
        categorias.add(categoria2);
        categorias.add(categoria3);

        subcategorias.add(subcategoria1);
        subcategorias.add(subcategoria2);
        subcategorias.add(subcategoria3);
        subcategorias.add(subcategoria4);
        subcategorias.add(subcategoria5);
        subcategorias.add(subcategoria6);
        subcategorias.add(subcategoria7);

        bancos.add(banco1);
        bancos.add(banco2);

        contasBancarias.add(contaBancaria1);
        contasBancarias.add(contaBancaria2);
        contasBancarias.add(contaBancaria3);
        contasBancarias.add(contaBancaria4);

        Transacao transacaoExemplo = new Transacao("Receber","Teste Outubro","10/10/2022",250.0,categoria1,subcategoria1,banco1,contaBancaria1);
        Transacao transacaoExemplo2 = new Transacao("Receber","Teste Novembro","10/11/2022",250.0,categoria1,subcategoria1,banco1,contaBancaria1);
        Transacao transacaoExemplo3 = new Transacao("Pagar","Teste Outubro Pagar","10/10/2022",250.0,categoria1,subcategoria1,banco1,contaBancaria1);
        transacoes.add(transacaoExemplo);
        transacoes.add(transacaoExemplo2);
        transacoes.add(transacaoExemplo3);
    }

}