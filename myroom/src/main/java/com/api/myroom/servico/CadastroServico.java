package com.api.myroom.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.myroom.entidade.cadastro;
import com.api.myroom.repositorio.RepositorioCadastro;

@Service
public class CadastroServico {

    @Autowired
    private RepositorioCadastro repositorioCadastro;

    public cadastro cadastraProfessor(cadastro novoCadastro) {
        return repositorioCadastro.save(novoCadastro);
    }

    public cadastro alterarProfessor(Long id, cadastro Cadastro) {

        var cadastroAtual = repositorioCadastro.findById(id);
        if (!cadastroAtual.isEmpty()) {
            Cadastro.setId(id);
            return repositorioCadastro.save(Cadastro);
        }
        return null;

    }

    public List<cadastro> buscarTodosCadastros() {
        return repositorioCadastro.findAll();
    }

    public cadastro buscarCadastroId(long id) {
        var cadastro = repositorioCadastro.findById(id);
        if (!cadastro.isEmpty()) {
            return cadastro.get();
        }
        return null;
    }

    public void excluirCadastroId(long id) {
        var cadastro = repositorioCadastro.findById(id);
        if (!cadastro.isEmpty()) {
            repositorioCadastro.deleteById(id);
        }

    }
}
