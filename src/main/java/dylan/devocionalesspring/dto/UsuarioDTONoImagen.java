package dylan.devocionalesspring.dto;

import dylan.devocionalesspring.entidades.Imagen;
import dylan.devocionalesspring.entidades.Usuario;
import dylan.devocionalesspring.enumeraciones.Rol;

public record UsuarioDTONoImagen(Long idUsuario,
                         String nombre,
                         String idImagenPerfil,
                         String idBannerPerfil,
                         Rol rol) {
    // Constructor auxiliar para mapear desde la entidad
    public UsuarioDTONoImagen(Usuario usuario) {
        this(usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getFotoPerfil().getId(),
                usuario.getBannerPerfil().getId(),
                usuario.getRol());
    }

}