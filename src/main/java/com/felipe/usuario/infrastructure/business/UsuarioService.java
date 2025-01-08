package com.felipe.usuario.infrastructure.business;

import com.felipe.usuario.infrastructure.entity.Usuario;
import com.felipe.usuario.infrastructure.exceptions.ConflictException;
import com.felipe.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Usuario salvaUsuario(Usuario usuario) {
        try {
            emailExiste(usuario.getEmail());
            return usuarioRepository.save(usuario);
        }
        catch(ConflictException e) {
            throw new ConflictException("Email já cadastrado", e.getCause());
        }
    }

    public void emailExiste(String email) {
        try {
            boolean exists = verificaUsuarioExistente(email);
            if(exists) {
                throw new ConflictException("Email já cadastrado");
            }
        }
        catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado", e.getCause());
        }
    }

    public boolean verificaUsuarioExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}
