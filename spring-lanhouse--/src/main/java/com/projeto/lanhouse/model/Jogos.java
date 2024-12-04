package com.projeto.lanhouse.model;

import jakarta.persistence.*;

@Entity
@Table(name = "jogos")
public class Jogos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String genero;
    private String desenvolvedor;

    @Column(name = "classificacao_etaria")
    private Integer classificacaoEtaria;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getDesenvolvedor() { return desenvolvedor; }
    public void setDesenvolvedor(String desenvolvedor) { this.desenvolvedor = desenvolvedor; }

    public Integer getClassificacaoEtaria() { return classificacaoEtaria; }
    public void setClassificacaoEtaria(Integer classificacaoEtaria) { this.classificacaoEtaria = classificacaoEtaria; }
}
