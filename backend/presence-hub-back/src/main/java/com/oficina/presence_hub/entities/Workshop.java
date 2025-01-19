package com.oficina.presence_hub.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Builder
@Table(name = Workshop.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class Workshop {

    public static final String TABLE_NAME= "WORKSHOP";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private boolean certificadosGerados;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Professor professor;

    @OneToMany(mappedBy = "workshop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participacao> participacoes;

    public String getDuration(){
        Duration duration = Duration.between(horaInicio, horaFim);

        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        return hours + " horas e " + minutes +" minutos";
    }
}
