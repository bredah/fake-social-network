package com.bredah.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bredah.web.model.Mensagem;
import com.bredah.web.service.MensagemService;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {

  private static final Logger log = LoggerFactory.getLogger(MensagemController.class);

  @Autowired
  private MensagemService mensagemService;

  @PostMapping()
  public ResponseEntity<Mensagem> criarMensagem(
      @RequestParam(value = "imagens", required = false) List<MultipartFile> imagens,
      @RequestParam("usuario") String usuario,
      @RequestParam("conteudo") String conteudo) {
    log.info("Publicar mensagem: usuário={}, conteúdo={}, imagens={}",
        usuario,
        conteudo,
        imagens);

    Mensagem mensagem = mensagemService.criarMensagem(usuario, conteudo, imagens);
    return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
  }

  // Exclusão de usuário
  @DeleteMapping("/{id}")
  public ResponseEntity<?> excluirMensagem(@PathVariable("id") Long id) {
    mensagemService.removerMensagem(id);
    return new ResponseEntity<>("Mensagem excluído com sucesso.", HttpStatus.OK);
  }


// @GetMapping("/imagem/{id}")
// public ResponseEntity<Resource> obterImagem(@PathVariable("id") String id) {
//     // Lógica para obter a imagem com o ID fornecido
//     // ...

//     // Verificar se a imagem foi encontrada
//     if (imagemEncontrada) {
//         // Carregar a imagem como um recurso Resource
//         Resource imagemResource = ... // Carregar a imagem como um recurso Resource

//         // Retornar a resposta com o recurso da imagem
//         return ResponseEntity.ok()
//                 .contentType(MediaType.IMAGE_PNG_VALUE) // Definir o tipo de mídia da resposta (pode ser adaptado conforme o tipo da imagem)
//                 .body(imagemResource);
//     } else {
//         // Caso a imagem não seja encontrada, retornar uma resposta de erro
//         return ResponseEntity.notFound().build();
//     }
// }

  @DeleteMapping("/{id}/imagens/{imagemId}")
  public ResponseEntity<Void> removerImagem(@PathVariable Long id, @PathVariable Long imagemId) {
    mensagemService.removerImagem(id, imagemId);
    return ResponseEntity.noContent().build();
  }
}
