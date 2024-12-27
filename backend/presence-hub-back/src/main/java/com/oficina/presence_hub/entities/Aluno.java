package com.oficina.presence_hub.entities;

import com.oficina.presence_hub.enums.SerieEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;



@Getter
@Setter
@Builder
@Entity
@Table(name = Aluno.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    public static final String TABLE_NAME = "ALUNO";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255, min = 3)
    @Column(nullable = false)
    private String nome;

    private String email;

    @NotNull
    @PastOrPresent(message = "{PastOrPresent.aluno.dataNascimento}")
    @DateTimeFormat(iso = ISO.DATE)
    @Column(name= "data_nascimento", nullable = false, columnDefinition = "DATE")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private SerieEnum serie;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    @Column(name = "instituicao_de_ensino")
    private String instituicaoDeEnsino;

    @Column(name = "telefone_contato")
    private String telefoneContato;

    @Column(name = "nome_responsavel")
    private String nomeResponsavel;

    @Column(name = "telefone_responsavel")
    private String telefoneResponsavel;

    @Column(name = "matricula_projeto")
    private String matriculaProjeto;

    @Column(name = "data_inscricao")
    private LocalDate dataInscricao;

    private String observacoes;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participacao> participacoes;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Certificado> certificados;

}