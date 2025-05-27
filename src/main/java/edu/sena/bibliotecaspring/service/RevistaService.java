package edu.sena.bibliotecaspring.service;

import edu.sena.bibliotecaspring.model.Revista; // Asegúrate de que tu modelo Revista esté aquí
import edu.sena.bibliotecaspring.repository.RevistaRepository; // Asegúrate de tener este import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; // Importante: Necesitas java.util.Optional

@Service // Anotación para la capa de servicio
public class RevistaService {

    @Autowired // Inyección de dependencia del repositorio
    private RevistaRepository revistaRepository;

    public List<Revista> findAll() {
        return revistaRepository.findAll();
    }

    // Este método devuelve un Optional<Revista> para manejar la posible ausencia de la revista
    public Optional<Revista> findById(Long id) {
        return revistaRepository.findById(id);
    }

    public Revista save(Revista revista) {
        return revistaRepository.save(revista);
    }

    public void deleteById(Long id) {
        revistaRepository.deleteById(id);
    }

    // Métodos de búsqueda personalizados que delegan al repositorio
    public List<Revista> findByTitulo(String titulo) {
        return revistaRepository.findByTitulo(titulo);
    }

    public List<Revista> findByCategoria(String categoria) { // Método para buscar por categoría
        return revistaRepository.findByCategoria(categoria);
    }

    public List<Revista> findByEditorial(String editorial) { // Método para buscar por editorial
        return revistaRepository.findByEditorial(editorial);
    }
}