package com.bredah.web.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.bredah.web.dto.MensagemRequest;
import com.bredah.web.entity.Imagem;
import com.bredah.web.entity.Mensagem;
import com.bredah.web.exception.MensagemNotFoundException;
import com.bredah.web.repository.MensagemRepository;

@Service
public class MensagemServiceImpl implements MensagemService {

  @Autowired
  private MensagemRepository mensagemRepository;

  @Autowired
  private ImagemService imagemService;


  @Override
  public Mensagem criarMensagem(Mensagem mensagem) {
    return mensagemRepository.save(mensagem);
  }

  public Mensagem criarMensagem(MensagemRequest mensagemRequest) {
    Mensagem mensagem = new Mensagem();
    mensagem.setConteudo(mensagemRequest.getConteudo());

    List<Imagem> imagens = new ArrayList<Imagem>();
    for (MultipartFile imagemAnexada : mensagemRequest.getImagens()) {
      Imagem imagem = imagemService.salvarImagem(imagemAnexada);
      imagens.add(imagem);
    }
    mensagem.setImagens(imagens);
    return mensagemRepository.save(mensagem);
  }


  @Override
  public void removerImagem(Long mensagemId, Long imagemId) {
    Mensagem mensagem = mensagemRepository.findById(mensagemId)
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
