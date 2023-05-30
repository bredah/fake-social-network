package com.bredah.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bredah.web.exception.ImagemNotFoundException;
import com.bredah.web.model.Imagem;
import com.bredah.web.repository.ImagemRepository;

@Service
public class ImagemServiceImpl implements ImagemService {

  @Autowired
  private ImagemRepository imagemRepository;


  @Override
  public Imagem salvarImagem(MultipartFile imagemFile) {
    String nomeImagem = imagemFile.getOriginalFilename();
    Imagem imagem = new Imagem();
    imagem.setNome(nomeImagem);
    return imagemRepository.save(imagem);
  }

  @Override
  public void removerImagem(Long imagemId) {
    imagemRepository.deleteById(imagemId);
  }

  @Override
  public Imagem buscarImagem(Long imagemId) {
    return imagemRepository.findById(imagemId)
        .orElseThrow(() -> new ImagemNotFoundException("Imagem não encontrada"));
  }
}
