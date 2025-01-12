package com.aluralatam.foro_hub.domain.topico;

import com.aluralatam.foro_hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String titulo;
    String mensaje;
    LocalDateTime fechaCreacion;
    String status;
    String curso;

    @ManyToOne
    @JoinColumn(name = "id_topico")
    Usuario id_usuario;
}
