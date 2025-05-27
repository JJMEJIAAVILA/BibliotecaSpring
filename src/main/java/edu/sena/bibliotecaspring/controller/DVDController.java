package edu.sena.bibliotecaspring.controller;

import edu.sena.bibliotecaspring.model.DVD; // Asegúrate de que tu modelo DVD esté aquí
import edu.sena.bibliotecaspring.service.DVDService; // Asegúrate de tener este import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List; // Importa List

@Controller // Anotación para controlador web (devuelve vistas)
@RequestMapping("/dvds") // Prefijo de URL para todas las peticiones a este controlador
public class DVDController {

    @Autowired
    private DVDService dvdService;

    // GET para listar todos los DVDs y mostrar en una lista web
    @GetMapping
    public String listarDVDs(Model model) {
        model.addAttribute("dvds", dvdService.findAll());
        return "dvds/lista"; // Nombre de la plantilla Thymeleaf para listar DVDs
    }

    // GET para mostrar el formulario para un nuevo DVD
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("dvd", new DVD());
        return "dvds/formulario"; // Nombre de la plantilla Thymeleaf para el formulario
    }

    // POST para guardar un nuevo DVD o actualizar uno existente desde el formulario web
    @PostMapping("/guardar")
    public String guardarDVD(@ModelAttribute DVD dvd) {
        dvdService.save(dvd);
        return "redirect:/dvds"; // Redirige a la lista de DVDs después de guardar
    }

    // GET para mostrar el formulario de edición de un DVD existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        // Usa .orElse(null) o .orElseThrow() porque findById devuelve Optional
        model.addAttribute("dvd", dvdService.findById(id).orElse(new DVD()));
        return "dvds/formulario"; // Reutiliza la misma plantilla de formulario
    }

    // GET para eliminar un DVD por su ID
    @GetMapping("/eliminar/{id}")
    public String eliminarDVD(@PathVariable Long id) {
        dvdService.deleteById(id);
        return "redirect:/dvds"; // Redirige a la lista de DVDs después de eliminar
    }

    // GET para buscar DVDs por género o director (desde la interfaz web)
    @GetMapping("/buscar")
    public String buscarPorGenero(
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) String director,
            Model model) {

        List<DVD> resultados;
        if (genero != null && !genero.isEmpty()) {
            resultados = dvdService.findByGenero(genero);
        } else if (director != null && !director.isEmpty()) {
            resultados = dvdService.findByDirector(director);
        } else {
            resultados = dvdService.findAll(); // Si no hay parámetros, muestra todos
        }
        model.addAttribute("dvds", resultados);
        return "dvds/lista"; // Devuelve la misma lista de DVDs con los resultados de la búsqueda
    }
}