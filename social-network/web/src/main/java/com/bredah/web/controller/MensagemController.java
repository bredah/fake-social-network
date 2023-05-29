package com.bredah.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bredah.web.dto.MensagemRequest;
import com.bredah.web.entity.Mensagem;
import com.bredah.web.service.MensagemService;

@RestController
@RequestMapping("/mensagens")
public class MensagemController {

  private static final Logger logger = LoggerFactory.getLogger(MensagemController.class);

  @Autowired
  private MensagemService mensagemService;
  
  @PostMapping
  public ResponseEntity<Mensagem> criarMensagem(@RequestBody Mensagem mensagem) {
    Mensagem novaMensagem = mensagemService.criarMensagem(mensagem);
    return ResponseEntity.status(HttpStatus.CREATED).body(novaMensagem);
  }

  @PostMapping
  public ResponseEntity<Mensagem> criarMensagem(@RequestBody MensagemRequest mensagemRequest) {
    Mensagem mensagem = mensagemService.criarMensagem(mensagemRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
  }

  @DeleteMapping("/{id}/imagens/{imagemId}")
  public ResponseEntity<Void> removerImagem(@PathVariable Long id, @PathVariable Long imagemId) {
    mensagemService.removerImagem(id, imagemId);
    return ResponseEntity.noContent().build();
  }
}

