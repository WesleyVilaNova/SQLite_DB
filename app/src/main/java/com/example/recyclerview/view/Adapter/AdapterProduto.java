package com.example.recyclerview.view.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.interfaces.Onclick;
import com.example.recyclerview.models.Produto;

import java.util.List;

public class AdapterProduto extends RecyclerView.Adapter<AdapterProduto.MyViewHolder> {

    private List<Produto> produtoList;
    private Onclick onclick;

    public AdapterProduto(List<Produto> produtoListaConstrutor,Onclick onclick) {
        this.produtoList = produtoListaConstrutor;
        this.onclick = onclick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produtos,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Produto produto = produtoList.get(position);

        holder.textPrincipal.setText(produto.getNome());
        holder.textSub.setText(String.valueOf(produto.getEstoque()));
        holder.valor.setText(String.valueOf(produto.getValor()));

        holder.itemView.setOnClickListener(v -> onclick.onClickListener(produto));

    }

    @Override
    public int getItemCount() {
        return produtoList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textPrincipal, textSub, valor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textPrincipal = itemView.findViewById(R.id.text_principal);
            textSub = itemView.findViewById(R.id.text_secundario);
            valor = itemView.findViewById(R.id.valor);

        }
    }
}
