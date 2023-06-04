package com.bredah.web.service;

import com.bredah.web.model.Usuario;

public interface UsuarioService {

  void cadastrarUsuario(Usuario usuario);

  Usuario consultarUsuarioPorId(Long id);

  boolean trocarSenhaUsuario(Long id, String novaSenha);

  void excluirUsuario(Long id);

  boolean bloquearUsuario(Long id);

  boolean desbloquearUsuario(Long id);
}
