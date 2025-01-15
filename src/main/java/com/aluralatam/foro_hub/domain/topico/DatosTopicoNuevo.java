package com.aluralatam.foro_hub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosTopicoNuevo(
        @NotNull
        String titulo,
        @NotNull
        String mensaje,
        @NotNull
        Long id_usuario,
        @NotNull
        String curso
) {
}
