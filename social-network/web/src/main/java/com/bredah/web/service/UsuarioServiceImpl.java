package com.bredah.web.service;


import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.bredah.web.entity.Usuario;
import com.bredah.web.exception.UsuarioExistenteException;
import com.bredah.web.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {


  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired(required = true)
  private BCryptPasswordEncoder passwordEncoder;

  private Map<Long, Usuario> usuarios;

  public UsuarioServiceImpl() {
    this.usuarios = new HashMap<>();
  }

  @Override
  public void cadastrarUsuario(Usuario usuario) {
    if (existeUsuario(usuario.getApelido())) {
      throw new UsuarioExistenteException("Usuário já cadastrado");
    }
    usuarios.put(usuario.getId(), usuario);
  }

  @Override
  public Usuario consultarUsuarioPorId(Long id) {
    return usuarios.get(id);
  }

  @Override
  public boolean trocarSenhaUsuario(Long id, String novaSenha) {
    Usuario usuario = usuarioRepository.findById(id).orElse(null);
    if (usuario != null) {
      if (passwordEncoder.matches(novaSenha, usuario.getSenha())) {
        // A nova senha é igual à senha atual
        return false;
      } else {
        usuario.setSenha(novaSenha);
        usuarioRepository.save(usuario);
        return true;
      }
    }
    return false;
  }

  @Override
  public void excluirUsuario(Long id) {
    usuarios.remove(id);
  }

  @Override
  public boolean bloquearUsuario(Long id) {
    Usuario usuario = usuarios.get(id);
    if (usuario != null) {
      usuario.setBloqueado(true);
      return true;
    }
    return false;
  }

  @Override
  public boolean desbloquearUsuario(Long id) {
    Usuario usuario = usuarios.get(id);
    if (usuario != null) {
      usuario.setBloqueado(false);
      return true;
    }
    return false;
  }

  public boolean existeUsuario(String apelido) {
    return usuarioRepository.findByApelido(apelido).isPresent();
  }
}
