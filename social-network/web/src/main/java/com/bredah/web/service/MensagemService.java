package com.bredah.web.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.bredah.web.dto.MensagemRequest;
import com.bredah.web.model.Mensagem;

public interface MensagemService {

  Mensagem criarMensagem(Mensagem mensagem);

  Mensagem criarMensagem(String usuario, String mensagem, List<MultipartFile> imagens);

  Mensagem criarMensagem(MensagemRequest mensagemRequest);
  
  Page<Mensagem> obterTodasAsMensagensEmOrdemDescrescente(Pageable pageable);

  void removerMensagem(Long mensagemId);

  void removerImagem(Long mensagemId, Long imagemId);
}
