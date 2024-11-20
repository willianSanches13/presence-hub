package com.oficina.presence_hub.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = Workshop.TABLE_NAME)
public class Workshop {

    public static final String TABLE_NAME= "WORKSHOP";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime data;
    @OneToMany
    private List<Participacao> participacoes;
}
