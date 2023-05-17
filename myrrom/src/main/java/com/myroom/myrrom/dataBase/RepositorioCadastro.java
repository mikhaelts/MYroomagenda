package com.myroom.myrrom.dataBase;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myroom.myrrom.entidades.Cadastro;

public interface RepositorioCadastro extends JpaRepository<Cadastro,Long> {
    
}
