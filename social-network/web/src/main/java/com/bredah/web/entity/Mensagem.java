package com.bredah.web.entity;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Mensagem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  @Column(nullable = false)
  private String conteudo;

  // @OneToMany(mappedBy = "mensagem", cascade = CascadeType.ALL, orphanRemoval = true)
  // private List<ImagemMensagem> imagens;
  
  private List<Imagem> imagens;
  
  @Column(nullable = false)
  private int gostei = 0;

  @Column(nullable = false)
  private int naoGostei = 0;

}
