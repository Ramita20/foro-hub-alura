package com.aluralatam.foro_hub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    @Query("""
            SELECT t FROM Topico t
            WHERE t.titulo = :titulo
            AND t.mensaje = :mensaje
            """)
    Topico findByTituloAndMensaje(String titulo, String mensaje);


    Page<Topico> findByActivoTrue(Pageable pagina);
}
