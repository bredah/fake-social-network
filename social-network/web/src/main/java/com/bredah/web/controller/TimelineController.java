package com.bredah.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bredah.web.model.Mensagem;
import com.bredah.web.service.MensagemService;

@RestController
@RequestMapping("/timeline")
public class TimelineController {
  
  private static final Logger log = LoggerFactory.getLogger(TimelineController.class);

  @Autowired
  private MensagemService mensagemService;

  @GetMapping("/mensagens")
  public ResponseEntity<Page<Mensagem>> listarMensagens(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {

    Pageable pageable = PageRequest.of(page, size);
    Page<Mensagem> mensagens = mensagemService.obterTodasAsMensagensEmOrdemDescrescente(pageable);

    return ResponseEntity.ok(mensagens);
  }

}
