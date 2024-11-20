package com.oficina.presence_hub.repositories;

import com.oficina.presence_hub.entities.Participacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipacaoRepository extends JpaRepository<Participacao, Long> {
}
