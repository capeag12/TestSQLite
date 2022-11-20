package com.example.testsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION=1;
    private static final String NOMBREBBDD = "test.db";

    public DBHelper(Context context) {
        super(context, NOMBREBBDD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE \"Usuarios\" (\n" +
                "\t\"USUARIO\"\tTEXT,\n" +
                "\t\"PASS\"\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(\"USUARIO\")\n" +
                ")");

        sqLiteDatabase.execSQL("CREATE TABLE \"Cosa\" (\n" +
                "\t\"ID\"\tINTEGER,\n" +
                "\t\"Nombre\"\tTEXT UNIQUE,\n" +
                "\tPRIMARY KEY(\"ID\")\n" +
                ")");
        System.out.println("Se creó la BBDD");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //Así se realiza un insert
    public long registrarUsuario(String usu, String contraseña){
        long id = 0;
        try {
            SQLiteDatabase bbdd = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("USUARIO",usu);
            values.put("PASS",contraseña);
            id=bbdd.insert("Usuarios",null,values);

            System.out.println("Se ");
        } catch (Exception ex){
            System.err.println("Algo falló");
        }


        return id;
    }
    //Así se realiza un select
    public HashMap<String, String> buscarUsuarios(){
        SQLiteDatabase bbdd = this.getWritableDatabase();
        HashMap<String,String> mapaUsuario= new HashMap<>();
        Cursor c = bbdd.rawQuery("Select * from Usuarios",null);

        //Así podemos recorrer un cursor
        while(c.isAfterLast()==false){
            mapaUsuario.put(c.getString(0),c.getString(1));
            c.moveToNext();
        }


        c.close();
        return mapaUsuario;
    }
}
