package com.oficina.presence_hub.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = Aluno.TABLE_NAME)
public class Aluno {

    public static final String TABLE_NAME= "ALUNO";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String email;
    private String senha;

    @OneToMany(mappedBy = "aluno")
    private List<Participacao> participacoes;

    @OneToMany(mappedBy = "aluno")
    private List<Certificado> certificados;

}
