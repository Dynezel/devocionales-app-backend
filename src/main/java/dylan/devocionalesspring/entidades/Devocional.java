package dylan.devocionalesspring.entidades;

import dylan.devocionalesspring.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Devocional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String contenido;
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private int vistas = 0;

    @ManyToOne
    private Usuario autor;

    @OneToMany(mappedBy = "devocionalId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MeGusta> meGustas = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "devocional_comentarios",
            joinColumns = @JoinColumn(name = "devocional_id"),
            inverseJoinColumns = @JoinColumn(name = "comentarios_id")
    )
    private List<Comentario> comentarios = new ArrayList<>();

    // Método para agregar un comentario a la lista
    public void agregarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }
    public UsuarioDTO hacerAutorDTO(Usuario autor) {
        return new UsuarioDTO(autor.getIdUsuario(),
                autor.getNombre(),
                autor.getEmail(),
                autor.getEmail(),
                autor.getNombreUsuario(),
                autor.getBiografia(),
                autor.getCelular(),
                autor.getContrasenia(),
                autor.getFotoPerfil(),
                autor.getBannerPerfil(),
                autor.getRol());
    }
}

