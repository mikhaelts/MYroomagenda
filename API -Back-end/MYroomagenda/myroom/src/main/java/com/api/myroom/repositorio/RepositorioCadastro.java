package com.api.myroom.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.myroom.entidade.cadastro;

public interface RepositorioCadastro extends JpaRepository<cadastro, Long> {

}
