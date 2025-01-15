package com.aluralatam.foro_hub.domain.controller;

import com.aluralatam.foro_hub.domain.topico.*;
import com.aluralatam.foro_hub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public TopicoController(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository){
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DatosTopicoDetalle> nuevoTopico(@RequestBody @Valid DatosTopicoNuevo datos,
                                                          UriComponentsBuilder uriComponentsBuilder) {
        var topicoRepetido = topicoRepository.findByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if(topicoRepetido != null) {
            throw new RuntimeException("El tópico está repetido.");
        }
        var usuarioTopicoNuevo = usuarioRepository.getReferenceById(datos.id_usuario());
        var topicoNuevo = topicoRepository.save(new Topico(datos, usuarioTopicoNuevo));

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicoNuevo.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosTopicoDetalle(topicoNuevo));
    }


    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(@PageableDefault(size = 5) Pageable pagina){
        return ResponseEntity.ok(topicoRepository.findByActivoTrue(pagina).map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> listarTipico(@PathVariable @NotNull Long id){
        var topicoBuscado = topicoRepository.findById(id);
        if(topicoBuscado.isEmpty()){
            throw new RuntimeException("No existe tópico con ese id.");
        }
        return ResponseEntity.ok(new DatosListadoTopico(topicoBuscado.get()));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@PathVariable @NotNull Long id, @RequestBody DatosActualizarTopico datos){
        var topicoBuscado = topicoRepository.findById(id);
        if(topicoBuscado.isEmpty()){
            throw new RuntimeException("No existe tópico con ese id.");
        }
        var topico = topicoBuscado.get();
        topico.actualizarDatos(datos);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    //DELETE LÓGICO.
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable @NotNull Long id){
        var topicoBuscado = topicoRepository.findById(id);
        if(topicoBuscado.isEmpty()){
            throw new RuntimeException("No existe tópico con ese id.");
        }
        topicoBuscado.get().desactivarTopico();
        return ResponseEntity.noContent().build();
    }
}
