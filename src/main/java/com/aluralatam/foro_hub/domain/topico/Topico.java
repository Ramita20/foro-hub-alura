package com.aluralatam.foro_hub.domain.topico;

import com.aluralatam.foro_hub.domain.usuario.Usuario;
import com.aluralatam.foro_hub.domain.usuario.UsuarioRepository;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

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
    @JoinColumn(name = "usuario_id")
    Usuario usuario;

    boolean activo;

    public Topico(DatosTopicoNuevo datos, Usuario usuario) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = "Abierto";
        this.curso = datos.curso();
        this.usuario = usuario;
        this.usuario.agregarTopico(this);
        this.activo = true;
    }

    public void actualizarDatos(DatosActualizarTopico datos) {
        if(datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if(datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if(datos.status() != null) {
            this.status = datos.status();
        }
        if(datos.curso() != null) {
            this.curso = datos.curso();
        }
    }

    public void desactivarTopico() {
        this.activo = false;
    }
}
