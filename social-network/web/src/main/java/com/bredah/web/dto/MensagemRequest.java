package com.bredah.web.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bredah.web.model.Mensagem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
@Data
public class MensagemRequest {
  private String usuario;
  private String conteudo;
  private List<MultipartFile> imagens;

  public Mensagem toMessagem() {
    return Mensagem.builder()
        .conteudo(conteudo)
        .usuario(usuario)
        .build();
  }
}
