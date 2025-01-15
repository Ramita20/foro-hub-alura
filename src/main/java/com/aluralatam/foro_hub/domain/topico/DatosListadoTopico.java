package com.aluralatam.foro_hub.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        String status,
        Long usuario_id,
        String curso
) {
    public DatosListadoTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),topico.getFechaCreacion(),
                topico.getStatus(), topico.getUsuario().getId(), topico.getCurso());
    }
}
