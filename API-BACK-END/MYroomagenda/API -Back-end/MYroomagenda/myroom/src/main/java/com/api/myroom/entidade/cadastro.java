package com.api.myroom.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class cadastro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 300)
    private String nome;
    @Column(nullable = false, length = 300)
    private String matricula;
    @Column(nullable = false, length = 50)
    private String senha;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



public void setMatricula(String matricula) {
    this.matricula = matricula;
}

public String getMatricula() {
    if (matricula != null) {
        return matricula.toUpperCase();
    } else {
        return null; // ou uma string vazia, dependendo do seu requisito
    }
}



    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public cadastro() {

    }

    @Override
    public String toString() {
        return "Cadastro [nome: " + nome + ", matricula: " + matricula + ", senha: " + senha + "]";
    }

    public String Matricula() {
        return null;
    }
}
