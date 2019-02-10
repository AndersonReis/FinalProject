//******************************************************

//Instituto Federal de São Paulo - Campus Sertãozinho

//Disciplina......: M4DADM

//Programação de Computadores e Dispositivos Móveis

//Aluno...........: Anderson dos reis cardoso

//******************************************************
package com.example.anderson.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


//classe que da suporte a interface grafica de capa
public class MainActivity extends AppCompatActivity {

    Button btcadastramento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btcadastramento  = (Button) findViewById(R.id.btcadastramento);

        btcadastramento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastramento();
            }
        });

    }

    //intent para ir para a tela de cadstramento
    void cadastramento(){

        Intent intent = new Intent();
        intent.setClass(MainActivity.this, Main2Activity.class);
        startActivity(intent);
        finish();

    }
}
