package com.api.myroom.controlador;

import java.util.List;

import javax.xml.crypto.Data;

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

import com.api.myroom.entidade.Agendamento;
import com.api.myroom.repositorio.AgendamentoRepositorio;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepositorio agendamentoRepositorio;

    @PostMapping("/agendamento")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String criarAgendamento(@RequestBody Agendamento novoAgendamento) {

        if (novoAgendamento == null || !agendamentoValido(novoAgendamento)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agendamento inválido");
        }

        Agendamento agendamentoCriado = agendamentoRepositorio.save(novoAgendamento);
        if (agendamentoCriado.getId() > 0) {
            return "Agendamento criado com sucesso";
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao criar agendamento");
    }

    @GetMapping("/agenda/all")
    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepositorio.findAll();
    }

    @DeleteMapping("/{id}")
    public String excluir(@PathVariable Data id) {
        agendamentoRepositorio.deleteById(id);
        return "Agendamento excluído com sucesso";
    }

    @PutMapping("/{id}")
    public Agendamento alterar(@RequestBody Agendamento novoAgendamento, @PathVariable Data id) {
        Agendamento agendamentoExistente = agendamentoRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado"));

        if (agendamentoValido(novoAgendamento)) {
            agendamentoExistente.setAgendarData(novoAgendamento.getAgendarData());
            agendamentoExistente.setHorario(novoAgendamento.getHorario());
            return agendamentoRepositorio.save(agendamentoExistente);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos para alteração do agendamento");
    }

    private boolean agendamentoValido(Agendamento agendamento) {
        return agendamento.getAgendarData() != null && agendamento.getHorario() != null;
    }

}
