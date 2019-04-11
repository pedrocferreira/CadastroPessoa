package com.example.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity {
    Cursor cursor;



    @Override

    //esa active é para listar e visualizar uma pessoa pesquisada por cpf

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        final BancoControler crud = new BancoControler(getBaseContext());
        cursor = crud.listaPessoa();
        String CPF = this.getIntent().getStringExtra("CPF"); // se na itent exitir um extra cpf ele busca por cpf
        ///senoa lista tudo
        if(CPF == null) {
            if (cursor.getCount() != 0) {
                //monta a listview
                String[] nomeCampos = new String[]{DbHelper.NOME, DbHelper.CPF, DbHelper.EMAIL,DbHelper.FONE,DbHelper.IDADE, DbHelper.ID, DbHelper.NASCIMENTO};
                int[] idViews = new int[]{R.id.txtNome, R.id.txtCpf, R.id.txtEmail,R.id.txtFone,R.id.txtIdade, R.id.txtID, R.id.txtNac};
                final SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.lista_layout, cursor, nomeCampos, idViews, 0);
                final ListView lista = (ListView) findViewById(R.id.lista);
                lista.setAdapter(adaptador);

                ///com um cliclongo vc remove o lemento da lista

                lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                        String codigo;
                        cursor.moveToPosition(position);
                        codigo = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.ID));
                        crud.deletarPessoa(Integer.valueOf(codigo));
                        Toast.makeText(getApplicationContext(), "Removido.", Toast.LENGTH_SHORT).show();




                        return false;
                    }
                });

                //por algum motivo o adaptador nao esta renovando a lista
                adaptador.notifyDataSetChanged();

                }
            else{

                Toast.makeText(getApplicationContext(), "Nenhuma Pessoa encontrado.", Toast.LENGTH_SHORT).show();
            }

            }
        else{
            cursor = crud.consultaPorCPF(CPF);
            if (cursor.getCount() != 0) {
                String[] nomeCampos = new String[]{DbHelper.NOME, DbHelper.CPF, DbHelper.EMAIL,DbHelper.FONE,DbHelper.IDADE, DbHelper.ID, DbHelper.NASCIMENTO};
                int[] idViews = new int[]{R.id.txtNome, R.id.txtCpf, R.id.txtEmail,R.id.txtFone,R.id.txtIdade, R.id.txtID, R.id.txtNac};
                SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.lista_layout, cursor, nomeCampos, idViews, 0);
                ListView lista = (ListView) findViewById(R.id.lista);
                lista.setAdapter(adaptador);


                ///com um cliclongo vc remove o lemento da lista

                lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                        String codigo;
                        cursor.moveToPosition(position);
                        codigo = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.ID));
                        crud.deletarPessoa(Integer.valueOf(codigo));
                        Toast.makeText(getApplicationContext(), "Removido.", Toast.LENGTH_SHORT).show();




                        return false;
                    }
                    //por algum motivo o adaptador nao esta renovando a lista
                   //     adaptador.notifyDataSetChanged();
                });

            }
            else{

                Toast.makeText(getApplicationContext(), "Nenhuma Pessoa encontrado.", Toast.LENGTH_SHORT).show();
            }





        }


        //chama o voltar

        Button btnVoltar = (Button)findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(ViewActivity.this,MainActivity.class);
                startActivity(it);
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
