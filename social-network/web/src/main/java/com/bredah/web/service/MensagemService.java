package com.bredah.web.service;

import com.bredah.web.dto.MensagemRequest;
import com.bredah.web.entity.Mensagem;

public interface MensagemService {

  Mensagem criarMensagem(Mensagem mensagem);

  Mensagem criarMensagem(MensagemRequest mensagemRequest);

  void removerMensagem(Long mensagemId);

  void removerImagem(Long mensagemId, Long imagemId);
}
