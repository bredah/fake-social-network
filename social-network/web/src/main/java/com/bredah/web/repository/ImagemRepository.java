package com.bredah.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bredah.web.model.Imagem;
import com.bredah.web.model.Mensagem;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
  List<Imagem> findByMensagem(Mensagem mensagem);
}
