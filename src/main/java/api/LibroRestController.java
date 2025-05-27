package edu.sena.bibliotecaspring.api; // Asegúrate de que el paquete sea 'api'

import edu.sena.bibliotecaspring.model.Libro;
import edu.sena.bibliotecaspring.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // ¡DESCOMENTA ESTA LÍNEA! Esta es la clave para REST.
@RequestMapping("/api/libros") // ¡DESCOMENTA ESTA LÍNEA! Este es el prefijo para tu API.
public class LibroRestController {

    @Autowired
    private LibroService libroService;

    // GET para listar todos los libros
    @GetMapping
    public ResponseEntity<List<Libro>> getAllLibros() {
        List<Libro> libros = libroService.findAll();
        if (libros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    // GET para obtener un libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable("id") Long id) {
        Optional<Libro> libroData = libroService.findById(id);
        return libroData.map(libro -> new ResponseEntity<>(libro, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST para crear un nuevo libro
    @PostMapping
    public ResponseEntity<Libro> createLibro(@RequestBody Libro libro) {
        try {
            Libro _libro = libroService.save(libro);
            return new ResponseEntity<>(_libro, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT para actualizar un libro existente
    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable("id") Long id, @RequestBody Libro libro) {
        Optional<Libro> libroData = libroService.findById(id);

        if (libroData.isPresent()) {
            Libro _libro = libroData.get();
            _libro.setTitulo(libro.getTitulo());
            _libro.setAutor(libro.getAutor());
            _libro.setIsbn(libro.getIsbn());
            _libro.setFechaPublicacion(libro.getFechaPublicacion());
            _libro.setNumeroPaginas(libro.getNumeroPaginas());
            return new ResponseEntity<>(libroService.save(_libro), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE para eliminar un libro
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLibro(@PathVariable("id") Long id) {
        try {
            libroService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Opcional: Buscar libros por título en la API
    @GetMapping("/search")
    public ResponseEntity<List<Libro>> searchLibrosByTitulo(@RequestParam String titulo) {
        List<Libro> libros = libroService.findByTitulo(titulo);
        if (libros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }
}