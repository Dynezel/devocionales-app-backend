package dylan.devocionalesspring.dto;

import dylan.devocionalesspring.entidades.Imagen;
import dylan.devocionalesspring.entidades.Usuario;
import dylan.devocionalesspring.enumeraciones.Rol;

public record UsuarioDTO(Long idUsuario,
                         String nombre,
                         String email,
                         String nombreUsuario,
                         String biografia,
                         String celular,
                         String contrasenia,
                         String contrasenia2,
                         Imagen fotoPerfil,
                         Imagen bannerPerfil,
                         Rol rol) {
    // Constructor auxiliar para mapear desde la entidad
    public UsuarioDTO(Usuario usuario) {
        this(usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getNombreUsuario(),
                usuario.getBiografia(),
                usuario.getCelular(),
                usuario.getContrasenia(),
                usuario.getContrasenia2(),
                usuario.getFotoPerfil(),
                usuario.getBannerPerfil(),
                usuario.getRol());
    }

}