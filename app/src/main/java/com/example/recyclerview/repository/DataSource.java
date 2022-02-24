package com.example.recyclerview.repository;

import com.example.recyclerview.models.Produto;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public List<Produto> produtoList(){
        List<Produto> listaDeProdutos = new ArrayList<>();

        Produto produto1 = new Produto();
        produto1.setNome("Tênis Nike ALFA");
        produto1.setEstoque(10);
        produto1.setValor(599.99);

        Produto produto2 = new Produto();
        produto2.setNome("Bola futebol Adidas ");
        produto2.setEstoque(2034);
        produto2.setValor(799.99);

        Produto produto3 = new Produto();
        produto3.setNome("Chuteira Nike");
        produto3.setEstoque(2);
        produto3.setValor(999.99);

        Produto produto4 = new Produto();
        produto4.setNome("Rede Campo");
        produto4.setEstoque(22);
        produto4.setValor(239.99);


        listaDeProdutos.add(produto1);
        listaDeProdutos.add(produto2);
        listaDeProdutos.add(produto3);
        listaDeProdutos.add(produto4);
        listaDeProdutos.add(produto1);
        listaDeProdutos.add(produto2);
        listaDeProdutos.add(produto3);
        listaDeProdutos.add(produto2);
        listaDeProdutos.add(produto3);
        listaDeProdutos.add(produto4);
        listaDeProdutos.add(produto1);
        listaDeProdutos.add(produto2);
        listaDeProdutos.add(produto3);
        listaDeProdutos.add(produto4);

         return listaDeProdutos;
    }
}
