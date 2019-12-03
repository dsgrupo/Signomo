package duj.app.signomo.DAOS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import duj.app.signomo.Models.Avaliacao;
import duj.app.signomo.utils.ConexaoUtil;

public class AvaliacaoDAO {
    private SQLiteDatabase db;
    private ConexaoUtil conexao;

    public AvaliacaoDAO(Context context){
        conexao = new ConexaoUtil(context);
    }

    public String insereDado(Avaliacao avaliacao){
        ContentValues valores;
        long resultado;

        db = conexao.getWritableDatabase();
        valores = new ContentValues();
        valores.put(ConexaoUtil.USUARIO, avaliacao.getUsuario());
        valores.put(ConexaoUtil.DESCRICAO, avaliacao.getDescri());
        valores.put(ConexaoUtil.NOTA, avaliacao.getNota());
        valores.put(ConexaoUtil.RECOMMEND, avaliacao.getRecommend());

        // nullcolumnhack identificar coluna que aceite nulo
        resultado = db.insert(ConexaoUtil.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {conexao.ID,conexao.DESCRICAO};
        db = conexao.getReadableDatabase();
        cursor = db.query(conexao.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {conexao.ID,conexao.DESCRICAO,conexao.USUARIO,conexao.NOTA, conexao.RECOMMEND};
        String where = ConexaoUtil.ID + "=" + id;
        db = conexao.getReadableDatabase();
        cursor = db.query(ConexaoUtil.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(Avaliacao avaliacao){
        ContentValues valores;
        String where;

        db = conexao.getWritableDatabase();

        where = ConexaoUtil.ID + "=" + avaliacao.getId();

        valores = new ContentValues();
        valores.put(ConexaoUtil.DESCRICAO, avaliacao.getDescri());
        valores.put(ConexaoUtil.USUARIO, avaliacao.getUsuario());
        valores.put(ConexaoUtil.NOTA, avaliacao.getNota());
        valores.put(ConexaoUtil.RECOMMEND, avaliacao.getRecommend());

        db.update(ConexaoUtil.TABELA,valores,where,null);
        db.close();
    }

    public void deletaRegistro(Avaliacao avaliacao){
        String where = ConexaoUtil.ID + "=" + avaliacao.getId();
        db = conexao.getReadableDatabase();
        db.delete(ConexaoUtil.TABELA,where,null);
        db.close();
    }
}
