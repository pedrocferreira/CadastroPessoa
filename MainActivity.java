package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//chama o ADD

        Button btnAdd = (Button)findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(MainActivity.this,CadastroActivity.class);
                startActivity(it);
            }
        });


        //Chama o view
        Button btnView = (Button)findViewById(R.id.btnView);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(MainActivity.this,ViewActivity.class);
                startActivity(it);
            }
        });




        ///chama a pesquisa
        Button btnPesquisa = (Button)findViewById(R.id.btnPesquisa);

        btnPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //valida se foi digitado um cpf
                EditText cpf = (EditText) findViewById(R.id.editCPF);
                Intent it = new Intent(MainActivity.this,ViewActivity.class);
                if(cpf.getText().toString().length()>0){
                    it.putExtra("CPF", cpf.getText().toString());
                    startActivity(it);
                }else{
                    Toast.makeText(getApplicationContext(), "Informe um CPF para pesquisar.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
