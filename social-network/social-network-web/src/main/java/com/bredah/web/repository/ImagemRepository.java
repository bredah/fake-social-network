package com.bredah.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bredah.web.model.Imagem;
import com.bredah.web.model.Mensagem;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long> {
  List<Imagem> findByMensagem(Mensagem mensagem);
}
