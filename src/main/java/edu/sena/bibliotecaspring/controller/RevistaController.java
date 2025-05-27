package edu.sena.bibliotecaspring.controller;

import edu.sena.bibliotecaspring.model.Revista; // Asegúrate de que tu modelo Revista esté aquí
import edu.sena.bibliotecaspring.service.RevistaService; // Asegúrate de tener este import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List; // Importa List

@Controller // Anotación para controlador web (devuelve vistas)
@RequestMapping("/revistas") // Prefijo de URL para todas las peticiones a este controlador
public class RevistaController {

    @Autowired // Inyección de dependencia del servicio
    private RevistaService revistaService;

    // GET para listar todas las revistas y mostrar en una lista web
    @GetMapping
    public String listarRevistas(Model model) {
        model.addAttribute("revistas", revistaService.findAll());
        return "revistas/lista"; // Nombre de la plantilla Thymeleaf para listar revistas
    }

    // GET para mostrar el formulario para una nueva revista
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("revista", new Revista());
        return "revistas/formulario"; // Nombre de la plantilla Thymeleaf para el formulario
    }

    // POST para guardar una nueva revista o actualizar una existente desde el formulario web
    @PostMapping("/guardar")
    public String guardarRevista(@ModelAttribute Revista revista) {
        revistaService.save(revista);
        return "redirect:/revistas"; // Redirige a la lista de revistas después de guardar
    }

    // GET para mostrar el formulario de edición de una revista existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        // Usa .orElse(null) o .orElseThrow() porque findById devuelve Optional
        model.addAttribute("revista", revistaService.findById(id).orElse(new Revista()));
        return "revistas/formulario"; // Reutiliza la misma plantilla de formulario
    }

    // GET para eliminar una revista por su ID
    @GetMapping("/eliminar/{id}")
    public String eliminarRevista(@PathVariable Long id) {
        revistaService.deleteById(id);
        return "redirect:/revistas"; // Redirige a la lista de revistas después de eliminar
    }

    // GET para buscar revistas por categoría o editorial (desde la interfaz web)
    @GetMapping("/buscar")
    public String buscarPorCategoria(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String editorial,
            Model model) {

        List<Revista> resultados;
        if (categoria != null && !categoria.isEmpty()) {
            resultados = revistaService.findByCategoria(categoria); // Aquí es donde te faltaba el método en el servicio
        } else if (editorial != null && !editorial.isEmpty()) {
            resultados = revistaService.findByEditorial(editorial); // Aquí también te faltaba el método
        } else {
            resultados = revistaService.findAll(); // Si no hay parámetros, muestra todos
        }
        model.addAttribute("revistas", resultados);
        return "revistas/lista"; // Devuelve la misma lista de revistas con los resultados de la búsqueda
    }
}