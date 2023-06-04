package com.bredah.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bredah.core.model.Imagem;
import com.bredah.core.model.Mensagem;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long> {
  List<Imagem> findByMensagem(Mensagem mensagem);
}
