package com.api.myroom.repositorio;

import javax.xml.crypto.Data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.myroom.entidade.Agendamento;

public interface AgendamentoRepositorio extends JpaRepository<Agendamento, Data> {

}
