package edu.sena.bibliotecaspring.api;

import edu.sena.bibliotecaspring.model.Revista;
import edu.sena.bibliotecaspring.service.RevistaService; // Asegúrate de tener este servicio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/revistas") // Endpoint para revistas
public class RevistaRestController {

    @Autowired
    private RevistaService revistaService; // Inyecta el servicio de Revista

    // GET para listar todas las revistas
    @GetMapping
    public ResponseEntity<List<Revista>> getAllRevistas() {
        List<Revista> revistas = revistaService.findAll();
        if (revistas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(revistas, HttpStatus.OK);
    }

    // GET para obtener una revista por ID
    @GetMapping("/{id}")
    public ResponseEntity<Revista> getRevistaById(@PathVariable("id") Long id) {
        Optional<Revista> revistaData = revistaService.findById(id);
        return revistaData.map(revista -> new ResponseEntity<>(revista, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST para crear una nueva revista
    @PostMapping
    public ResponseEntity<Revista> createRevista(@RequestBody Revista revista) {
        try {
            Revista _revista = revistaService.save(revista);
            return new ResponseEntity<>(_revista, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT para actualizar una revista existente
    @PutMapping("/{id}")
    public ResponseEntity<Revista> updateRevista(@PathVariable("id") Long id, @RequestBody Revista revista) {
        Optional<Revista> revistaData = revistaService.findById(id);

        if (revistaData.isPresent()) {
            Revista _revista = revistaData.get();
            _revista.setTitulo(revista.getTitulo());
            _revista.setFechaPublicacion(revista.getFechaPublicacion());
            _revista.setEditorial(revista.getEditorial());
            _revista.setCategoria(revista.getCategoria());
            _revista.setIssn(revista.getIssn()); // Asegúrate de que tu entidad Revista tenga un campo 'issn'
            return new ResponseEntity<>(revistaService.save(_revista), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE para eliminar una revista
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRevista(@PathVariable("id") Long id) {
        try {
            revistaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Opcional: Buscar revistas por título o categoría (ajusta según tu RevistaService)
    @GetMapping("/search")
    public ResponseEntity<List<Revista>> searchRevistas(@RequestParam(required = false) String titulo,
                                                        @RequestParam(required = false) String categoria) {
        List<Revista> revistas;
        if (titulo != null && !titulo.isEmpty()) {
            revistas = revistaService.findByTitulo(titulo); // Necesitarás este método en RevistaService
        } else if (categoria != null && !categoria.isEmpty()) {
            revistas = revistaService.findByCategoria(categoria); // Necesitarás este método en RevistaService
        } else {
            revistas = revistaService.findAll();
        }

        if (revistas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(revistas, HttpStatus.OK);
    }
}