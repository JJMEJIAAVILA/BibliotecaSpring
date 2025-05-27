package edu.sena.bibliotecaspring.service;

import edu.sena.bibliotecaspring.model.DVD; // Asegúrate de que tu modelo DVD esté aquí
import edu.sena.bibliotecaspring.repository.DVDRepository; // Asegúrate de tener este import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; // Importante: Asegúrate de tener este import para Optional

@Service
public class DVDService {

    @Autowired
    private DVDRepository dvdRepository;

    public List<DVD> findAll() {
        return dvdRepository.findAll();
    }

    public Optional<DVD> findById(Long id) { // Este método devuelve Optional<DVD>
        return dvdRepository.findById(id);
    }

    public DVD save(DVD dvd) {
        return dvdRepository.save(dvd);
    }

    public void deleteById(Long id) {
        dvdRepository.deleteById(id);
    }

    // Métodos de búsqueda personalizados:
    // Estos llaman a los métodos declarados en DVDRepository
    public List<DVD> findByTitulo(String titulo) {
        return dvdRepository.findByTitulo(titulo);
    }

    public List<DVD> findByGenero(String genero) { // Método para buscar por género
        return dvdRepository.findByGenero(genero);
    }

    public List<DVD> findByDirector(String director) { // Método para buscar por director
        return dvdRepository.findByDirector(director);
    }
}