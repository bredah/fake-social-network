package com.bredah.web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotEmpty(message = "apelido é obrigatório")
  @Column(nullable = false, unique = true)
  private String apelido;
  @NotEmpty(message = "e-mail é obrigatório")
  @Column(nullable = false, unique = true)
  @Email(message = "e-mail inválido")
  private String email;
  @NotEmpty(message = "nome não pode estar vazio")
  private String nome;
  @Size(min = 8, message = "senha deve ter no mínimo 8 caracteres")
  @Pattern(regexp = "^[a-zA-Z0-9]+$",
      message = "senha deve conter letras e números")
  private String senha;
  private boolean bloqueado;

  public Usuario(String apelido) {
    this.apelido = apelido;
    this.bloqueado = false;
  }

}
