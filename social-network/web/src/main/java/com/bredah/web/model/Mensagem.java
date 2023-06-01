package com.bredah.web.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@ToString
@Table(name = "mensagem")
public class Mensagem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // @ManyToOne
  // @JoinColumn(name = "usuario_id", referencedColumnName = "id")
  // @NotEmpty(message = "conteudo não pode estar vazio")
  // private Usuario usuario;
  @NotEmpty(message = "usuario não pode estar vazio")
  private String usuario;

  @Column(nullable = false)
  @NotEmpty(message = "conteudo não pode estar vazio")
  private String conteudo;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "mensagem_id")
  private List<Imagem> imagens;

  private LocalDateTime dataCriacao;

  private int gostei = 0;

  private int naoGostei = 0;

}
