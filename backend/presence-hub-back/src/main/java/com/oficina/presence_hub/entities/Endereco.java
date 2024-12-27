package com.oficina.presence_hub.entities;


import com.oficina.presence_hub.enums.UfEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = Endereco.TABLE_NAME)
public class Endereco {

    public static final String TABLE_NAME = "ENDERECOS";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;

    private String bairro;

    @NotBlank
    @Size(min = 3, max = 255)
    @Column(nullable = false)
    private String cidade;

    @NotNull
    @Column(nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private UfEnum uf;

    private String cep;

    private Integer numero;

    @Size(max = 255)
    private String complemento;

    @OneToOne(mappedBy = "endereco")
    private Aluno aluno;

}
