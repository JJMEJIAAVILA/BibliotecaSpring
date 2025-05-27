package edu.sena.bibliotecaspring.api;

import edu.sena.bibliotecaspring.model.DVD;
import edu.sena.bibliotecaspring.service.DVDService; // Asegúrate de tener este servicio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dvds") // Endpoint para DVDs
public class DVDRestController {

    @Autowired
    private DVDService dvdService; // Inyecta el servicio de DVD

    // GET para listar todos los DVDs
    @GetMapping
    public ResponseEntity<List<DVD>> getAllDVDs() {
        List<DVD> dvds = dvdService.findAll();
        if (dvds.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dvds, HttpStatus.OK);
    }

    // GET para obtener un DVD por ID
    @GetMapping("/{id}")
    public ResponseEntity<DVD> getDVDById(@PathVariable("id") Long id) {
        Optional<DVD> dvdData = dvdService.findById(id);
        return dvdData.map(dvd -> new ResponseEntity<>(dvd, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST para crear un nuevo DVD
    @PostMapping
    public ResponseEntity<DVD> createDVD(@RequestBody DVD dvd) {
        try {
            DVD _dvd = dvdService.save(dvd);
            return new ResponseEntity<>(_dvd, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT para actualizar un DVD existente
    @PutMapping("/{id}")
    public ResponseEntity<DVD> updateDVD(@PathVariable("id") Long id, @RequestBody DVD dvd) {
        Optional<DVD> dvdData = dvdService.findById(id);

        if (dvdData.isPresent()) {
            DVD _dvd = dvdData.get();
            _dvd.setTitulo(dvd.getTitulo());
            _dvd.setFechaPublicacion(dvd.getFechaPublicacion());
            _dvd.setDirector(dvd.getDirector()); // Asegúrate de que tu entidad DVD tenga un campo 'director'
            _dvd.setGenero(dvd.getGenero());       // Asegúrate de que tu entidad DVD tenga un campo 'genero'
            _dvd.setDuracion(dvd.getDuracion());   // Asegúrate de que tu entidad DVD tenga un campo 'duracion'
            return new ResponseEntity<>(dvdService.save(_dvd), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE para eliminar un DVD
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDVD(@PathVariable("id") Long id) {
        try {
            dvdService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Opcional: Buscar DVDs por título o director (ajusta según tu DVDService)
    @GetMapping("/search")
    public ResponseEntity<List<DVD>> searchDVDs(@RequestParam(required = false) String titulo,
                                                @RequestParam(required = false) String director) {
        List<DVD> dvds;
        if (titulo != null && !titulo.isEmpty()) {
            dvds = dvdService.findByTitulo(titulo); // Necesitarás este método en DVDService
        } else if (director != null && !director.isEmpty()) {
            dvds = dvdService.findByDirector(director); // Necesitarás este método en DVDService
        } else {
            dvds = dvdService.findAll();
        }

        if (dvds.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dvds, HttpStatus.OK);
    }
}