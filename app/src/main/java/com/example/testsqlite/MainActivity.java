package com.example.testsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.testsqlite.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        DBHelper bbdd = new DBHelper(MainActivity.this);
        SQLiteDatabase sqLite = bbdd.getWritableDatabase();

        if (sqLite!=null){
            Toast.makeText(this,"Se creó la BBDD",Toast.LENGTH_LONG);
        } else{
            Toast.makeText(this,"Error al crear la BBDD",Toast.LENGTH_LONG);
        }

        binding.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long id = bbdd.registrarUsuario(binding.txtUsu.getText().toString(), binding.txtContra.getText().toString());
                if (id>0){Toast.makeText(MainActivity.this, "Se añadió",Toast.LENGTH_LONG);}
            }
        });

        binding.btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(bbdd.buscarUsuarios().toString());
            }
        });
    }
}