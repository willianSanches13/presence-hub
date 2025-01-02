package com.oficina.presence_hub.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = Participacao.TABLE_NAME)
public class Participacao {

    public static final String TABLE_NAME= "PARTICIPACAO";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Aluno aluno;
    @ManyToOne
    private Workshop workshop;
    private boolean presente;
}
