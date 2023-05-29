package com.bredah.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bredah.web.entity.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
}
