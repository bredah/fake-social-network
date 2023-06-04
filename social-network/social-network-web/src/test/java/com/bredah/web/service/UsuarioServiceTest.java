package com.bredah.web.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bredah.web.model.Usuario;
import com.bredah.web.repository.UsuarioRepository;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCadastrarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setApelido("John Doe");
        usuario.setSenha("123456");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        usuarioService.cadastrarUsuario(usuario);

        verify(usuarioRepository, times(1)).save(usuario);

    }

    @Test
    public void testTrocarSenhaUsuario() {
        long id = 1L;
        String novaSenha = "654321";

        Usuario usuario = new Usuario();
        usuario.setId(id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        usuarioService.trocarSenhaUsuario(id, novaSenha);

        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, times(1)).save(usuario);

        assertThat(usuario.getSenha()).isEqualTo(novaSenha);
    }

    @Test
    public void testConsultarUsuarioPorId() {
        long id = 1L;

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setApelido("John Doe");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.consultarUsuarioPorId(id);

        verify(usuarioRepository, times(1)).findById(id);

        assertThat(resultado).isEqualTo(usuario);
    }

    @Test
    public void testDeletarUsuario() {
        long id = 1L;

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setApelido("John Doe");



        // Chamada do método deletarUsuario
        usuarioService.excluirUsuario(id);

        // Verificação se o método deleteById foi chamado com o ID correto
        verify(usuarioRepository, times(1)).deleteById(id);
    }

}
