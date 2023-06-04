package com.bredah.web.config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.bredah.web.model.Mensagem;
import com.bredah.web.repository.MensagemRepository;

@Component
@Profile("dev")
public class DataLoader implements CommandLineRunner {

  private final MensagemRepository mensagemRepository;

  public DataLoader(MensagemRepository mensagemRepository) {
    this.mensagemRepository = mensagemRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    var total = 20;
    for (int i = 0; i < total; i++) {
      var mensagem = Mensagem.builder()
          .usuario("Usuário: " + i)
          .conteudo("Conteúdo da mensagem " + i)
          .dataCriacao(LocalDateTime.now())
          .build();
      mensagemRepository.save(mensagem);
    }
  }
}
