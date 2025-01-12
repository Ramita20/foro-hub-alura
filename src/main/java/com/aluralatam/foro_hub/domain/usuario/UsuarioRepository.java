package com.aluralatam.foro_hub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByNombre(String nombreUsuario);
}
