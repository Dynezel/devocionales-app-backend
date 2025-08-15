package dylan.devocionalesspring.dto;

import dylan.devocionalesspring.entidades.Comentario;
import dylan.devocionalesspring.entidades.Devocional;
import dylan.devocionalesspring.entidades.MeGusta;

import java.time.LocalDateTime;
import java.util.List;

public record DevocionalConAutor(int id,
                                 String titulo,
                                 String contenido,
                                 LocalDateTime fechaCreacion,
                                 int vistas,
                                 UsuarioDTO autor,
                                 List<MeGusta> meGustas,
                                 List<Comentario> comentarios) {
    public DevocionalConAutor(Devocional devocional) {
        this(
                devocional.getId(), devocional.getTitulo(), devocional.getContenido(), devocional.getFechaCreacion(), devocional.getVistas(),
                devocional.hacerAutorDTO(devocional.getAutor()), devocional.getMeGustas(), devocional.getComentarios()
        );
    }
}
