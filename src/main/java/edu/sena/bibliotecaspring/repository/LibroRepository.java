package edu.sena.bibliotecaspring.repository;

import edu.sena.bibliotecaspring.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional; // <--- Asegúrate de tener este import si usas Optional directamente

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    // JpaRepository ya provee findById que devuelve Optional<T>
    List<Libro> findByTitulo(String titulo);
    List<Libro> findByAutor(String autor);
}