package dylan.devocionalesspring.dto;

import java.time.LocalDateTime;

public class MensajeDTO {
    private Long id;
    private String contenido;
    private LocalDateTime fechaEnvio;
    private UsuarioDTONoImagen emisor;
    private UsuarioDTONoImagen receptor;

    public MensajeDTO() {}

    public MensajeDTO(Long id, String contenido, LocalDateTime fechaEnvio,
                      UsuarioDTONoImagen emisor, UsuarioDTONoImagen receptor) {
        this.id = id;
        this.contenido = contenido;
        this.fechaEnvio = fechaEnvio;
        this.emisor = emisor;
        this.receptor = receptor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public UsuarioDTONoImagen getEmisor() {
        return emisor;
    }

    public void setEmisor(UsuarioDTONoImagen emisor) {
        this.emisor = emisor;
    }

    public UsuarioDTONoImagen getReceptor() {
        return receptor;
    }

    public void setReceptor(UsuarioDTONoImagen receptor) {
        this.receptor = receptor;
    }
}
