package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

         Button btnSalvar =(Button) findViewById(R.id.btnSalvar);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chama o metodo que cadastra
                cadastrar();
            }
        });



        //chama o voltar

        Button btnVoltar = (Button)findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(CadastroActivity.this,MainActivity.class);
                startActivity(it);
            }
        });





    }



    ///Metodo que cadastra a pessoa
    public void cadastrar(){
        String resultado;
        BancoControler crud = new BancoControler(getBaseContext());
        EditText campoNome, campoCPF, campoIdade,campoEmail,campoFone, campoNaci;
        campoNome = (EditText) findViewById(R.id.txtNome);
        campoCPF = (EditText) findViewById(R.id.txtCpf);
        campoIdade = (EditText) findViewById(R.id.txtIdade);
        campoEmail = (EditText) findViewById(R.id.txtID);
        campoFone = (EditText) findViewById(R.id.txtFone);
        campoNaci = (EditText) findViewById(R.id.txtNac);


        if(campoNome.getText().toString().trim().length() != 0) {
            if (campoCPF.getText().toString().length() == 11) {
                if(campoIdade.getText().toString().length() != 0) {

                    Pessoa p = new Pessoa(campoNome.getText().toString(),campoCPF.getText().toString(),campoIdade.getText().toString(),campoEmail.getText().toString(),campoFone.getText().toString(), campoNaci.getText().toString() );

                    campoNome.setText("");
                    campoCPF.setText("");
                    campoEmail.setText("");
                    campoIdade.setText("");
                    campoFone.setText("");
                    campoNaci.setText("");
                    resultado = crud.inserePessoa(p);

                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Digite uma idade válida", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Digite um cpf válido!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Digite um nome válido!", Toast.LENGTH_SHORT).show();
        }


    }
}
