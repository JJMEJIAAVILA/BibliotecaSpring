package edu.sena.bibliotecaspring.service;

import edu.sena.bibliotecaspring.model.Libro;
import edu.sena.bibliotecaspring.repository.LibroRepository; // Asegúrate de tener este import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; // <--- Importante: Necesitas Optional

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    public Optional<Libro> findById(Long id) { // <--- Asegúrate de que devuelva Optional
        return libroRepository.findById(id); // <--- Esto ya devuelve Optional por defecto en JpaRepository
    }

    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }

    public void deleteById(Long id) {
        libroRepository.deleteById(id);
    }

    public List<Libro> findByTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo);
    }

    public List<Libro> findByAutor(String autor) {
        return libroRepository.findByAutor(autor);
    }
}