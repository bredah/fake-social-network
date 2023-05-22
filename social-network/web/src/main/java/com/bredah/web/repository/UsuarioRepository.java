package com.bredah.web.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bredah.web.entity.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  Optional<Usuario> findByApelido(String apelido);
}
