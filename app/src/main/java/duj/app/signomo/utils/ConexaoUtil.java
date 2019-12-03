package duj.app.signomo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoUtil extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "avaliacoes";
    public static final String ID = "_id";
    public static final String USUARIO = "usuario";
    public static final String DESCRICAO = "descricao";
    public static final String NOTA = "1";
    public static final int VERSAO = 1;
    public static final String RECOMMEND = "1";

    public ConexaoUtil(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + USUARIO + " text,"
                + DESCRICAO + " text,"
                + NOTA + " text,"
                +RECOMMEND + "text"
                +")";
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
