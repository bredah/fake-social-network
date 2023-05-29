package com.bredah.web.dto;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class MensagemRequest {

  private String conteudo;
  private List<MultipartFile> imagens;

}
