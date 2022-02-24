package com.example.recyclerview.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclerview.DAO.ProdutoDAO;
import com.example.recyclerview.view.Adapter.AdapterProduto;
import com.example.recyclerview.R;
import com.example.recyclerview.interfaces.Onclick;
import com.example.recyclerview.models.Produto;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Onclick {

    private ProdutoDAO produtoDAO;
    private AdapterProduto adapterProduto;
    private SwipeableRecyclerView recyclerView;
    private List<Produto> produtoList = new ArrayList<>();
    private ImageButton ibAdicionar;
    private ImageButton ibVerMais;
    ImageView text_inf ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        produtoDAO = new ProdutoDAO(this);
        produtoList = produtoDAO.getListProduto();

        iniciarComponentes();
        adicionarProduto();
        ouvinteClick();

    }

    @Override
    protected void onStart() {
        super.onStart();
        iniciarRecyclerView();

    }

    private void iniciarComponentes() {
        ibAdicionar = findViewById(R.id.ib_adicionar);
        ibVerMais = findViewById(R.id.ib_mais);
        recyclerView = findViewById(R.id.recyclerView_produtos);
        text_inf = findViewById(R.id.text_inf);
    }

    private void adicionarProduto(){
        ibAdicionar.setOnClickListener(v -> {
            startActivity(new Intent(this, FormProdutoActivity.class));
        });
    }

    private void ouvinteClick(){
        ibVerMais.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this,ibVerMais);
            popupMenu.getMenuInflater().inflate(R.menu.menu_toolbar,popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(menuItem -> {
                if (menuItem.getItemId() == R.id.menu_sobre){
                    Toast.makeText(this, "Sobre", Toast.LENGTH_SHORT).show();
                }
                return true;
            });

            popupMenu.show();

        });
    }

    private void iniciarRecyclerView(){

        produtoList.clear();
        produtoList = produtoDAO.getListProduto();
        verificaQtdList();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapterProduto = new AdapterProduto(produtoList,this);
        recyclerView.setAdapter(adapterProduto);

        recyclerView.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {
            }

            @Override
            public void onSwipedRight(int position) {
                Produto produto = produtoList.get(position);

                produtoDAO.deletarProduto(produto);
                produtoList.remove(produto);
                adapterProduto.notifyItemRemoved(position);

                verificaQtdList();

            }
        });
    }

    public void verificaQtdList(){
        if (produtoList.size() == 0){
            text_inf.setVisibility(View.VISIBLE);

        }else{
            text_inf.setVisibility(View.GONE);

        }
    }

    @Override
    public void onClickListener(Produto produto) {
        //startActivity(new Intent(this,FormProdutoActivity.class));
        Intent intent = new Intent(this, FormProdutoActivity.class);
        intent.putExtra("produto" , produto);
        startActivity(intent);
    }
}