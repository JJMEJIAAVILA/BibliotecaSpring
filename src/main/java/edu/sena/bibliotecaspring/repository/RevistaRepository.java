package edu.sena.bibliotecaspring.repository;

import edu.sena.bibliotecaspring.model.Revista; // Asegúrate de que tu modelo Revista esté aquí
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional; // Opcional, JpaRepository lo maneja internamente para findById

@Repository // Anotación para la capa de repositorio
public interface RevistaRepository extends JpaRepository<Revista, Long> {

    // Spring Data JPA automáticamente crea la implementación para estos métodos
    // Asegúrate de que los campos 'titulo', 'categoria' y 'editorial'
    // existan como propiedades (con getters/setters) en tu clase de entidad 'Revista'.
    List<Revista> findByTitulo(String titulo);
    List<Revista> findByCategoria(String categoria); // <-- ¡Asegúrate de que esta línea esté presente!
    List<Revista> findByEditorial(String editorial); // <-- ¡Asegúrate de que esta línea esté presente!
}