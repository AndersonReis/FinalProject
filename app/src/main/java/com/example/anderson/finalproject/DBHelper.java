//******************************************************

//Instituto Federal de São Paulo - Campus Sertãozinho

//Disciplina......: M4DADM

//Programação de Computadores e Dispositivos Móveis

//Aluno...........: Anderson dos reis cardoso

//******************************************************


package com.example.anderson.finalproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private static final String DATABASE_NAME = "bandodedados.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "contato";
    private Context context;
    private SQLiteDatabase db;
    private SQLiteStatement insertStmt;
    private static final String INSERT = "insert into " + TABLE_NAME + " (nome, cpf, idade, telefone, email) values(?,?,?,?,?)";

    public DBHelper(Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(INSERT);
    }

    //Método de insersao de dados no BD
    public long insert(String nome, String cpf, String idade, String telefone, String email) {
        this.insertStmt.bindString(1, nome);
        this.insertStmt.bindString(2, cpf);
        this.insertStmt.bindString(3, idade);
        this.insertStmt.bindString(4, telefone);
        this.insertStmt.bindString(5, email);
        return this.insertStmt.executeInsert();
    }

    //Método de Delete de dados do BD
    public void deleteAll() {
        this.db.delete(TABLE_NAME, null, null);
    }

    //metodo para obter informacao do banco de dados - lista de contato
    public List<Contato> queryGetAll() {
        List<Contato> list = new ArrayList<Contato>();

        try {
            Cursor cursor = this.db.query(TABLE_NAME, new String[] {"nome", "cpf", "idade", "telefone", "email"},
                    null, null, null, null, null, null);
            int nregistros = cursor.getCount();

            if (nregistros != 0) {
                cursor.moveToFirst();

                do {
                    Contato contato = new Contato(cursor.getString(0), cursor.getInt(1), cursor.getInt(2),
                            cursor.getInt(3), cursor.getString(4));
                    list.add(contato);

                } while (cursor.moveToNext());

                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                    return list;
                } else
                    return null;
            }
        } catch (Exception err){
           // return null;
        }
        return list;
    }


    private static class OpenHelper extends SQLiteOpenHelper {
        OpenHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        //método para criacao da tabela no banco de dados
        public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +" (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, " +
                    " cpf INTEGER,  idade INTEGER,  telefone INTEGER, email TEXT); ";
            db.execSQL(sql);
        }

        //método que atualiza o banco de dados nas alteraçoes
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}