package com.oficina.presence_hub.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = Certificado.TABLE_NAME)
public class Certificado {

    public static final String TABLE_NAME= "CERTIFICADO";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Aluno aluno;
    @ManyToOne
    private Workshop workshop;
    private String assinaturaDigital; // Hash ou assinatura de verificação
    private LocalDate dataEmissao;
}
