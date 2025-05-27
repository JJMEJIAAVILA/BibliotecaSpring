package edu.sena.bibliotecaspring.repository;

import edu.sena.bibliotecaspring.model.DVD; // Asegúrate de que tu modelo DVD esté aquí
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional; // Opcional, JpaRepository lo maneja internamente para findById

@Repository // Anotación para la capa de repositorio
public interface DVDRepository extends JpaRepository<DVD, Long> {

    // Spring Data JPA automáticamente crea la implementación para estos métodos
    // Asegúrate de que los campos 'titulo', 'genero' y 'director'
    // existan como propiedades (con getters/setters) en tu clase de entidad 'DVD'.
    List<DVD> findByTitulo(String titulo);
    List<DVD> findByGenero(String genero); // Declaración para buscar DVDs por género
    List<DVD> findByDirector(String director); // Declaración para buscar DVDs por director
}