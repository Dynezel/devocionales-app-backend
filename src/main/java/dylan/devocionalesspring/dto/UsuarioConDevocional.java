package dylan.devocionalesspring.dto;


import dylan.devocionalesspring.entidades.Devocional;
import dylan.devocionalesspring.entidades.Imagen;
import dylan.devocionalesspring.enumeraciones.Rol;
import jakarta.persistence.*;

import java.util.List;

public record UsuarioConDevocional(
        Long idUsuario,
        String nombre,
        String email,
        String nombreUsuario,
        String biografia,
        String celular,
        String contrasenia,
        String contrasenia2,
        Imagen fotoPerfil,
        Imagen bannerPerfil,
        Rol rol,
        List<Devocional> devocionales
) {

}
