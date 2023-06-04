package com.bredah.core.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.bredah.core.model.Mensagem;

@DataJpaTest
@ActiveProfiles("dev")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MensagemRepositoryIT {

  @Autowired
  private MensagemRepository mensagemRepository;

  @Test @Tag("slow")
  @Order(1)
  public void devePermitirCriarTabela() {
    long totalTabelasCriada = mensagemRepository.count();
    assertThat(totalTabelasCriada).isGreaterThanOrEqualTo(0);
  }

  @Test
  @Order(2)
  @Tag("integration")
  public void devePermitirRegistrarMensagem() {
    Mensagem savedMensagem = criarRegistro();
    assertThat(savedMensagem.getId()).isNotNull();
  }

  @Test
  @Order(3)
  public void devePermitirLerMensagem() {
    // regitrar mensagem
    Mensagem savedMensagem = criarRegistro();
    // verificar se a mensagem existente
    Optional<Mensagem> retrievedMensagem = mensagemRepository.findById(savedMensagem.getId());
    assertThat(retrievedMensagem).isPresent();
  }

  @Test
  @Order(3)
  public void devePermitirAlterarMensagem() {
    // regitrar mensagem
    Mensagem savedMensagem = criarRegistro();
    // atualizar mensagem
    Mensagem updatedMensagem = savedMensagem;
    updatedMensagem.setConteudo("Conteúdo alterado da mensagem de teste");
    // verificar se a atualização foi efetuada
    Mensagem savedUpdatedMensagem = mensagemRepository.save(updatedMensagem);
    assertThat(savedUpdatedMensagem.getConteudo()).isEqualTo(updatedMensagem.getConteudo());
  }

  @Test
  @Order(3)
  public void devePermitirRemoverMensagem() {
    // regitrar mensagem
    Mensagem savedMensagem = criarRegistro();
    // verificar se a mensagem foi removida
    mensagemRepository.delete(savedMensagem);
    assertThat(mensagemRepository.findById(savedMensagem.getId())).isEmpty();
  }

  private Mensagem criarMensagem() {
    return Mensagem.builder()
        .usuario("Usuário Teste")
        .conteudo("Conteúdo da mensagem de teste")
        .build();
  }

  private Mensagem criarRegistro() {
    var mensagem = criarMensagem();
    return mensagemRepository.save(mensagem);
  }

}
