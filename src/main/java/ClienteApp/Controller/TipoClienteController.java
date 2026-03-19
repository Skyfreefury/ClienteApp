/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClienteApp.Controller;

import ClienteApp.Model.TipoCliente;
import ClienteApp.Service.TipoClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author christianmogena3
 */
@Controller
public class TipoClienteController {

    private final TipoClienteService tipoClienteService;

    @Autowired
    public TipoClienteController(TipoClienteService tipoClienteService) {
        this.tipoClienteService = tipoClienteService;
    }

    // Listar todos los tipos
    @GetMapping("/tiposcliente")
    public String getAllTipoCliente(Model model) {
        List<TipoCliente> tipos = tipoClienteService.getAllTipoCliente();
        model.addAttribute("tipos", tipos);
        return "tipoclienteHTML"; // He visto que tienes este archivo en tus templates
    }

    // Mostrar formulario para crear uno nuevo
    @GetMapping("/tiposcliente/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tipoCliente", new TipoCliente());
        return "tipoclienteForm"; // Crearemos este HTML en el siguiente paso
    }

    // Guardar el nuevo tipo
    @PostMapping("/tiposcliente/guardar")
    public String guardarTipoCliente(@ModelAttribute TipoCliente tipoCliente) {
        // Aprovechamos tu método guardar que hace Insert o Update dependiendo si tiene ID
        tipoClienteService.guardarTipoCliente(tipoCliente);
        return "redirect:/tiposcliente";
    }

    // Mostrar formulario para editar
    @GetMapping("/tiposcliente/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        TipoCliente tipo = tipoClienteService.getforID(id);
        model.addAttribute("tipoCliente", tipo);
        return "tipoclienteForm"; 
    }

    // Actualizar (apuntamos al mismo sitio que guardar por simplicidad)
    @PostMapping("/tiposcliente/actualizar")
    public String actualizarTipoCliente(@ModelAttribute TipoCliente tipoCliente) {
        tipoClienteService.actualizarTipoCliente(tipoCliente);
        return "redirect:/tiposcliente";
    }

    // Eliminar
    @GetMapping("/tiposcliente/eliminar/{id}")
    public String eliminarTipoCliente(@PathVariable int id) {
        tipoClienteService.eliminarTipoCliente(id);
        return "redirect:/tiposcliente";
    }
}
