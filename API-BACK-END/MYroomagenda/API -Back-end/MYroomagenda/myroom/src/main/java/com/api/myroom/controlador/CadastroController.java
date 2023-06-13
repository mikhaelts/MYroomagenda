package com.api.myroom.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.api.myroom.entidade.cadastro;
import com.api.myroom.servico.CadastroServico;


@RestController
@RequestMapping("/cadastros")
public class CadastroController {

    @Autowired
    private CadastroServico cadastroProfessor;

    @PostMapping("/cadastro")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String cadastrar(@RequestBody cadastro novoCadastro) {

        if (novoCadastro == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Professor não cadastrado. Verifique os dados enviados!");
        }

        if (professorValido(novoCadastro)) {
            var cadastroCriado = cadastroProfessor.cadastraProfessor(novoCadastro);
            if (cadastroCriado.getId() > 0) {
                return "Professor cadastrado com sucesso!";
            }
        }

        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar o professor.");
    }

    @DeleteMapping("/cadastrado/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String excluir(@PathVariable long id) {
        cadastroProfessor.excluirCadastroId(id);
        return "Professor excluído com sucesso!";
    }

    @GetMapping("/cadastros")
    public List<cadastro> buscarCadastro() {
        return cadastroProfessor.buscarTodosCadastros();
    }

    @GetMapping("/cadastro/{id}")
    public cadastro buscaCadastroPorId(@PathVariable long id) {
        cadastro cadastro = cadastroProfessor.buscarCadastroId(id);
        if (cadastro != null && cadastro.getId() > 0) {
            return cadastro;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cadastro não encontrado. Verifique os dados!");
    }

    @PutMapping("/cadastro/{id}")
    public cadastro alterar(@RequestBody cadastro novoCadastro, @PathVariable long id) {
        cadastro cadastroAlterado = cadastroProfessor.alterarProfessor(id, novoCadastro);
        if (cadastroAlterado != null && cadastroAlterado.getId() > 0) {
            return cadastroAlterado;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível alterar o cadastro. Verifique os dados!");
    }

    private boolean professorValido(cadastro novoCadastro) {
        if (novoCadastro == null) {
            return false;
        }

        if (novoCadastro.getNome() == null || novoCadastro.getNome().trim().isEmpty()) {
            return false;
        }

        if (novoCadastro.getMatricula() == null || novoCadastro.getMatricula().trim().isEmpty()) {
            return false;
        }

        if (novoCadastro.getSenha() == null || novoCadastro.getSenha().trim().isEmpty()) {
            return false;
        }

        return true;
    }
}
