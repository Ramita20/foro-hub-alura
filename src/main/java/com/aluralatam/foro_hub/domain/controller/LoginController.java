package com.aluralatam.foro_hub.domain.controller;

import com.aluralatam.foro_hub.domain.usuario.DatosLoginUsuario;
import com.aluralatam.foro_hub.domain.usuario.Usuario;
import com.aluralatam.foro_hub.infra.security.autentication.DatosTokenJWT;
import com.aluralatam.foro_hub.infra.security.autentication.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, TokenService tokenService){
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<DatosTokenJWT> autenticarUsuario(@RequestBody @Valid DatosLoginUsuario datos) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datos.nombre(),datos.contrasena());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var tokenJWT = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }
}
