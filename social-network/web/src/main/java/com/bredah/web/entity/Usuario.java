package com.bredah.web.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String apelido;
  private String email;
  private String senha;
  private boolean bloqueado;

  public Usuario(String apelido, String senha) {
    this.apelido = apelido;
    setSenha(senha);
    this.bloqueado = false;
  }

  public void setSenha(String senha) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    this.senha = encoder.encode(senha);
  }

}
