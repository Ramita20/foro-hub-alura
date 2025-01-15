package com.aluralatam.foro_hub.domain.topico;

public record DatosTopicoDetalle(
        Long id,
        String titulo,
        String mensaje,
        String status,
        String curso
) {
    public DatosTopicoDetalle(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getStatus(), topico.getCurso());
    }
}
