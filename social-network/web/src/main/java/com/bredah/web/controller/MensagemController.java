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
import com.bredah.web.model.Mensagem;
import com.bredah.web.service.MensagemService;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {

  private static final Logger log = LoggerFactory.getLogger(MensagemController.class);

  @Autowired
  private MensagemService mensagemService;

  @PostMapping(consumes = { "application/json" })
  public ResponseEntity<Mensagem> criarMensagem(@RequestBody MensagemRequest mensagemRequest) {
    log.info("Publicar mensagem: usuário={}, conteúdo={}, imagens={}",
        mensagemRequest.getUsuario(),
        mensagemRequest.getConteudo(),
        mensagemRequest.getImagens().size());

    Mensagem mensagem = mensagemService.criarMensagem(mensagemRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
  }

  @DeleteMapping("/{id}/imagens/{imagemId}")
  public ResponseEntity<Void> removerImagem(@PathVariable Long id, @PathVariable Long imagemId) {
    mensagemService.removerImagem(id, imagemId);
    return ResponseEntity.noContent().build();
  }
}
