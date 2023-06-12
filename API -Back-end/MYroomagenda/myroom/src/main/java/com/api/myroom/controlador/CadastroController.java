package com.api.myroom.controlador;

import java.util.List;

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
@RequestMapping("/api")
public class CadastroController {

    private CadastroServico cadastroProfessor;

    @PostMapping("/cadastro/")
    @ResponseStatus(code = HttpStatus.CREATED)
    private String cadastrar(@RequestBody cadastro novoCadastro) {

        if (novoCadastro == null) {
            return String.format("Professor não cadastrado. Verifique dados enviados! ", HttpStatus.BAD_REQUEST);
        }

        if (professorValido(novoCadastro)) {
            var cadastroCriado = cadastroProfessor.cadastraProfessor(novoCadastro);
            if (cadastroCriado.getId() > 0) {
                return String.format("Professor cadastrado com sucesso!", HttpStatus.CREATED);
            }
        }
        return null;
    }

    @DeleteMapping("/cadastrado/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String excluir(@PathVariable long id) {

        cadastroProfessor.excluirCadastroId(id);
        cadastro cadastro = cadastroProfessor.buscarCadastroId(id);
        if (cadastro == null || cadastro.getId() <= 0) {
            return String.format("Professor excluido com sucesso!", HttpStatus.CREATED);
        }

        return String.format("Professor não excluido. Verifique dados enviados! ", HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("")
    public List<cadastro> buscarCadastro() {
        return cadastroProfessor.buscarTodosCadastros();

    }

    @GetMapping("/cadastro/{id}")

    public cadastro buscaCadastroPorId(@PathVariable long id) {
        cadastro cadastro = cadastroProfessor.buscarCadastroId(id);
        if (cadastro != null && cadastro.getId() > 0) {
            return cadastro;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cadastro n encontrado. verifique seu dados!");
    }

    @PutMapping("/cadastro/{id}")
    public cadastro alterar(@RequestBody cadastro cadastro, @PathVariable long id) {
        cadastro cadastroaAlterado = cadastroProfessor.alterarProfessor(id, cadastro);
        if (cadastroaAlterado != null && cadastroaAlterado.getId() > 0) {
            return cadastroaAlterado;
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cadastro n alterado. verifique seu dados!");
    }

    private boolean professorValido(cadastro novoCadastro) {

        if (novoCadastro == null) {
            return false;
        }

        if (novoCadastro.getNome().trim().length() <= 0) {
            return false;
        }
        if (novoCadastro.getMatricula().trim().length() <= 0) {
            return false;
        }
        if (novoCadastro.getSenha().trim().length() <= 0) {
            return false;
        }
        return true;
    }

}
