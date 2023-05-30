package com.bredah.web.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bredah.web.dto.MensagemRequest;
import com.bredah.web.exception.MensagemNotFoundException;
import com.bredah.web.exception.ValidationException;
import com.bredah.web.model.Imagem;
import com.bredah.web.model.Mensagem;
import com.bredah.web.repository.MensagemRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Service
public class MensagemServiceImpl implements MensagemService {

  @Autowired
  private MensagemRepository mensagemRepository;

  @Autowired
  private ImagemService imagemService;

  @Autowired
  private Validator validator;

  @Override
  public Mensagem criarMensagem(Mensagem mensagem) {
    return mensagemRepository.save(mensagem);
  }

  public Mensagem criarMensagem(MensagemRequest mensagemRequest) {
    Mensagem mensagem = mensagemRequest.toMessagem();

    Set<ConstraintViolation<Mensagem>> violations = validator.validate(mensagem);
    Set<ConstraintViolation<?>> genericViolations = new HashSet<>();

    for (ConstraintViolation<Mensagem> violation : violations) {
      genericViolations.add(violation);
    }

    if (!genericViolations.isEmpty()) {
      throw new ValidationException("Erros de validação encontrados", genericViolations);

    }

    List<Imagem> imagens = new ArrayList<Imagem>();
    if (imagens.size() > 0) {
      for (MultipartFile imagemAnexada : mensagemRequest.getImagens()) {
        Imagem imagem = imagemService.salvarImagem(imagemAnexada);
        imagens.add(imagem);
      }
      mensagem.setImagens(imagens);
    }
    return mensagemRepository.save(mensagem);
  }

  @Override
  public void removerImagem(Long mensagemId, Long imagemId) {
    Mensagem mensagem = mensagemRepository
        .findById(mensagemId)
        .orElseThrow(() -> new MensagemNotFoundException("Mensagem não encontrada"));
    Imagem imagem = imagemService.buscarImagem(imagemId);
    // Verifique se a imagem está associada à mensagem correta
    if (!mensagem.getImagens().contains(imagem)) {
      throw new IllegalArgumentException("A imagem não está associada à mensagem especificada");
    }
    // Remova a imagem da mensagem e do repositório de imagens
    imagemService.removerImagem(imagemId);
  }

  @Override
  public void removerMensagem(Long mensagemId) {
    mensagemRepository.deleteById(mensagemId);
  }

}
