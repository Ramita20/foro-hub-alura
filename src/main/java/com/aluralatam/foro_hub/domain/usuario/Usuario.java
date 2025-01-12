package com.aluralatam.foro_hub.domain.usuario;

import com.aluralatam.foro_hub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nombre;
    String correoElectronico;
    String contrasena;

    @OneToMany(mappedBy = "id_usuario", fetch = FetchType.LAZY)
    List<Topico> topicos;
}
