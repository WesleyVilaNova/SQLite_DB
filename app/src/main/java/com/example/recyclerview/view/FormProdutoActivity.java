package com.example.recyclerview.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recyclerview.DAO.ProdutoDAO;
import com.example.recyclerview.R;
import com.example.recyclerview.models.Produto;

public class FormProdutoActivity extends AppCompatActivity {

    EditText edit_nome , edit_valor, edit_quantidade;
    private ProdutoDAO produtoDAO;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_produto);

        iniciandoCompentes();
        produtoDAO = new ProdutoDAO(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            produto = (Produto) bundle.getSerializable("produto");
            editaProduto();

        }

    }
    public void iniciandoCompentes(){
        edit_nome = findViewById(R.id.edit_produto);
        edit_quantidade = findViewById(R.id.edit_quantidade);
        edit_valor = findViewById(R.id.edit_valor);
    }

    private void editaProduto(){
        edit_nome.setText(produto.getNome());
        edit_quantidade.setText(String.valueOf(produto.getEstoque()));
        edit_valor.setText(String.valueOf(produto.getValor()));
    }

    public void salvarProduto(View view) {
        String nome = edit_nome.getText().toString();
        String quantidade = edit_quantidade.getText().toString();
        String valor = edit_valor.getText().toString();

            if (!nome.isEmpty()){
                if (!quantidade.isEmpty()){
                    int qtd = Integer.parseInt(quantidade);
                    if (qtd >= 1) {
                        if (!valor.isEmpty()){
                            double valorProduto = Double.parseDouble(valor);
                            if (valorProduto >=1){

                                if (produto == null) produto = new Produto();
                                produto.setNome(nome);
                                produto.setEstoque(qtd);
                                produto.setValor(valorProduto);

                                if (produto.getId() != 0){
                                    produtoDAO.atualizarProduto(produto);
                                }else {
                                    produtoDAO.salvarProduto(produto);
                                }

                                Toast.makeText(this, "Produto Cadastrado com sucesso", Toast.LENGTH_LONG).show();
                                edit_nome.setText("");
                                edit_quantidade.setText("");
                                edit_valor.setText("");
                                finish();


                            }else {
                                edit_valor.requestFocus();
                                edit_valor.setError("Informe o preço do produto");
                            }
                        }else {
                            edit_valor.requestFocus();
                            edit_valor.setError("Informe um valor válido");
                        }
                    }else {
                        edit_quantidade.requestFocus();
                        edit_quantidade.setError("Informe um valor maior que 0 (zero)");
                    }
                }else {
                    edit_quantidade.requestFocus();
                    edit_quantidade.setError("Informe a Quantidade");
                }

            }else {
                edit_nome.requestFocus();
                edit_nome.setError("Informe o produto");
            }
    }

    public void voltarPag(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}