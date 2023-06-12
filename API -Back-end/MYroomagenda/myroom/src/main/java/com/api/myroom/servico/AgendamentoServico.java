package com.api.myroom.servico;

import java.util.List;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.myroom.entidade.Agendamento;
import com.api.myroom.repositorio.AgendamentoRepositorio;

@Service
public class AgendamentoServico {

    @Autowired
    private AgendamentoRepositorio agendamentoRepositorio;

    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepositorio.findAll();
    }

    public Agendamento criarAgendamento(Agendamento novoAgendamento) {
        return agendamentoRepositorio.save(novoAgendamento);
    }

    public void excluirAgendamento(Data id) {
        agendamentoRepositorio.deleteById(id);
    }

    public Agendamento alterarAgendamento(Data id, Agendamento novoAgendamento) {
        Agendamento agendamentoExistente = agendamentoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        agendamentoExistente.setAgendarData(novoAgendamento.getAgendarData());
        agendamentoExistente.setHorario(novoAgendamento.getHorario());

        return agendamentoRepositorio.save(agendamentoExistente);
    }
}
