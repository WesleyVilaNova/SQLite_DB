package com.example.recyclerview.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.recyclerview.DbHelper.DataBaseHelper;
import com.example.recyclerview.models.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO { // 1

    private final SQLiteDatabase write;
    private final SQLiteDatabase read;

    public ProdutoDAO(Context context) {
        DataBaseHelper dataBaseHelper =  new DataBaseHelper(context);
        this.write = dataBaseHelper.getWritableDatabase();
        this.read = dataBaseHelper.getReadableDatabase();
    }

    public void salvarProduto (Produto produto){
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COLUMN_NAME, produto.getNome());
        cv.put(DataBaseHelper.COLUMN_ESTOQUE, produto.getEstoque());
        cv.put(DataBaseHelper.COLUMN_VALOR, produto.getValor());

        try {
            write.insert(DataBaseHelper.TB_PRODUTO,null, cv);
            //write.close();
        }catch (Exception e){
            Log.e("ERROR" , "Ocorreu um error no ContentValue" + e.getMessage());
        }

    }

    public List<Produto> getListProduto(){

        List<Produto> produtoList = new ArrayList<>();

        String sql = "SELECT * FROM " + DataBaseHelper.TB_PRODUTO + ";";
        Cursor cursor = read.rawQuery(sql,null);

        try {
        while (cursor.moveToNext()){

            //int id  = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COLUMN_ID));
            String nome = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_NAME));
            int estoque = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COLUMN_ESTOQUE));
            Double valor = cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.COLUMN_VALOR));

            Produto produto = new Produto();
            produto.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COLUMN_ID)));
            produto.setNome(nome);
            produto.setEstoque(estoque);
            produto.setValor(valor);

            produtoList.add(produto);
        }
        }catch (Exception e){
            Log.e("ERROR" , "Ocorreu um error ao ler DB" +e.getMessage());
        }

        return produtoList;
    }

    public void atualizarProduto(Produto produto){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.COLUMN_NAME, produto.getNome());
        contentValues.put(DataBaseHelper.COLUMN_ESTOQUE,produto.getValor());
        contentValues.put(DataBaseHelper.COLUMN_VALOR, produto.getValor());

        String where = "id=?";
        String[] args = {String.valueOf(produto.getId())};

        try {
            write.update(DataBaseHelper.TB_PRODUTO, contentValues, where, args);
            //write.close();
        }catch (Exception e){
            Log.e("ERROR" , "Error ao Atualizar produto" +e.getMessage());
        }

    }


    public void deletarProduto(Produto produto){
        try{
            String[] args = {String.valueOf(produto.getId())};
            //String where = "id=?";
            write.delete(DataBaseHelper.TB_PRODUTO,"id=?",args);
        }catch (Exception e){
            Log.e("ERROR" , "Ocorreu um erro no metodo deleteProduto" + e.getMessage());
        }

    }

}
