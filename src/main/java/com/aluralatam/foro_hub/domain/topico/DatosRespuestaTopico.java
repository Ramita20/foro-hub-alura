package com.aluralatam.foro_hub.domain.topico;

public record DatosRespuestaTopico(
        String titulo,
        String mensaje,
        String status,
        String curso
) {
    public DatosRespuestaTopico(Topico topico){
        this(topico.getTitulo(), topico.getMensaje(), topico.getStatus(), topico.getCurso());
    }
}
