package com.example.sqlite.interfaces;
import com.example.sqlite.Pessoa;

import java.util.List;

public interface PessoaDAO {
    String inserePessoa(Pessoa p);
    void save (Pessoa pessoa);
    void deletarPessoa (Pessoa p);
    void  consultaPorCPF(Pessoa p);
    void consultaPorID(Pessoa p);
    void listaPessoa(Pessoa p);


}
