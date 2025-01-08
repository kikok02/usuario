package com.felipe.usuario.infrastructure.repository;

import com.felipe.usuario.infrastructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

        // Método para buscar um usuário pelo e-mail
        Optional<Usuario> findByEmail(String email);

        boolean existsByEmail(String email);
}
