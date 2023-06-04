package com.bredah.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bredah.core.model.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
  @Query("SELECT m FROM Mensagem m ORDER BY m.dataCriacao DESC")
  Page<Mensagem> obterTodasAsMensagensEmOrdemDescrescente(Pageable pageable);
}
