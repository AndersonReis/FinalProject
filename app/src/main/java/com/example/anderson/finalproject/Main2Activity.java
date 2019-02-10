//******************************************************

//Instituto Federal de São Paulo - Campus Sertãozinho

//Disciplina......: M4DADM

//Programação de Computadores e Dispositivos Móveis

//Aluno...........: Anderson dos reis cardoso

//******************************************************

package com.example.anderson.finalproject;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.List;



//essa classe chama todos os campos de digitação e botoes da segunda interface grafica
public class Main2Activity extends AppCompatActivity {

    private DBHelper dh;

    EditText edtnome, edtcpf, edtidade, edttelefone, edtemail;
    Button btinserrir, btvoltar, btlistar, btdelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dh = new DBHelper(this);

        edtnome = (EditText) findViewById(R.id.edtnome);
        edtcpf = (EditText) findViewById(R.id.edtcpf);
        edtidade = (EditText) findViewById(R.id.edtidade);
        edttelefone = (EditText) findViewById(R.id.edttelefone);
        edtemail = (EditText) findViewById(R.id.edtemail);

        btinserrir = (Button) findViewById(R.id.btinserir);
        btlistar = (Button) findViewById(R.id.btlistar);
        btvoltar = (Button) findViewById(R.id.btvoltar);
        btdelete = (Button ) findViewById(R.id.btdelete);


        btvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retornoprincipal();
            }
        });


        btdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dh.deleteAll();
            }
        });


        //tratando o botao inserir
        btinserrir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (edtnome.getText().length() > 0 && edtcpf.getText().length() > 0 && edtidade.getText().length() > 0
                        && edttelefone.getText().length() > 0 && edtemail.getText().length() > 0) {
                    dh.insert(edtnome.getText().toString(), edtcpf.getText().toString(), edtidade.getText().toString(), edttelefone.getText().toString(),
                            edtemail.getText().toString());
                    AlertDialog.Builder adb = new AlertDialog.Builder(Main2Activity.this);
                    adb.setTitle("Sucesso");
                    adb.setMessage("cadastro realizado!!");
                    adb.show();
                    edtnome.setText("");
                    edtcpf.setText("");
                    edtidade.setText("");
                    edttelefone.setText("");
                    edtemail.setText("");
                } else {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Main2Activity.this);
                    adb.setTitle("**ERRO**");
                    adb.setMessage("OS CAMPOS PRECISAM SER TODOS SER PREENCHIDOS!!");
                    adb.show();
                }
            }
        });

        //tratando o botao listar dados do BD
        btlistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Contato> contatos = dh.queryGetAll();
                if (contatos == null) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Main2Activity.this);
                    adb.setTitle("Mensagem!!");
                    adb.setMessage("NAO HA REGISTRO CADASTRADOS!!");
                    adb.show();
                    edtnome.setText("");
                    edtcpf.setText("");
                    edtidade.setText("");
                    edttelefone.setText("");
                    edtemail.setText("");
                    return;
                }

                for (int i = 0; i < contatos.size(); i++) {
                    Contato contato = (Contato) contatos.get(i);
                    AlertDialog.Builder adb = new AlertDialog.Builder(Main2Activity.this);
                    adb.setTitle("registro " + i);
                    adb.setMessage("Nome: "+ contato.getNome()+"\ncpf: "+ contato.getCpf()+"\nidade: "+ contato.getIdade()+"\ntelefone: "+
                            contato.getTelefone()+"\nemail: "+contato.getEmail());
                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    adb.show();
                }
            }
        });
    }

    //funcao que contem uma intenção, a qual retorna para a tela anterior
    void  retornoprincipal(){

        Intent intent = new Intent();
        intent.setClass(Main2Activity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}