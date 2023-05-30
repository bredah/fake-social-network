package com.bredah.web.service;

import org.springframework.web.multipart.MultipartFile;

import com.bredah.web.model.Imagem;

public interface ImagemService {

  public Imagem salvarImagem(MultipartFile imagemFile);

  public Imagem buscarImagem(Long imagemId);

  public void removerImagem(Long imagemId);
}
