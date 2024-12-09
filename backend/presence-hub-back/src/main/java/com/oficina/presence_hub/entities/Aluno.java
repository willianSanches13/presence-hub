package com.oficina.presence_hub.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = Aluno.TABLE_NAME)
public class Aluno {

    public static final String TABLE_NAME = "ALUNO";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private Integer idade;
    private String serie;
    private String instituicaoDeEnsino;
    private String telefoneContato;
    private String cidade;
    private String estado;
    private String nomeResponsavel;
    private String telefoneResponsavel;
    private String matriculaProjeto;
    private String statusParticipacao;
    private LocalDate dataInscricao;
    private String observacoes;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participacao> participacoes;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Certificado> certificados;

}