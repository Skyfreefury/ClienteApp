/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClienteApp.Controller;

import ClienteApp.Model.Client;
import ClienteApp.Service.ClientService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author christianmogena3
 */
@Controller
public class ClientController {
    private final ClientService clientService;
    
    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }
    @GetMapping({"/", "/clientes"})
    public String getAllClient(Model model) {
        List<Client> uno = clientService.getAllClient();
        System.out.println(uno.size());
        model.addAttribute("clientes",uno);
        return "index";
    }
    @GetMapping("/clientes/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cliente", new Client());
        return "form";
    }
    @Value("${app.upload.dir}")
    private String uploadDir;

    @PostMapping("/clientes/guardar")
    public String guardarAlumno(@ModelAttribute Client client,
        @RequestParam("archivo") MultipartFile archivo) throws IOException {
        if (archivo != null && !archivo.isEmpty()) {
            String nombre = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
            Path dir = Paths.get(uploadDir);
            Files.createDirectories(dir);
            Files.copy(archivo.getInputStream(), dir.resolve(nombre),
                StandardCopyOption.REPLACE_EXISTING);
            client.setFoto(nombre);
        }
        clientService.guardarClient(client);
        return "redirect:/clientes";
    }
    @GetMapping("/clientes/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        Client client = clientService.getforID(id);
        model.addAttribute("cliente", client);
        return "form";
    }
    
    @PostMapping("/clientes/actualizar")
public String actualizarCliente(@ModelAttribute Client client,
                @RequestParam("archivo") MultipartFile archivo) throws IOException {

    Client clientExistente = clientService.getforID(client.getId());

    if (archivo != null && !archivo.isEmpty()) {
        // 1. Borrar foto antigua si existe
        if (clientExistente.getFoto() != null) {
            Path archivoAntiguo = Paths.get(uploadDir)
                    .resolve(clientExistente.getFoto());
            Files.deleteIfExists(archivoAntiguo);
        }
        // 2. Guardar nueva foto
        String nombre = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();

        Path dir = Paths.get(uploadDir);
        Files.createDirectories(dir);

        Files.copy(archivo.getInputStream(), dir.resolve(nombre),
                StandardCopyOption.REPLACE_EXISTING);
        client.setFoto(nombre);
    }
    else {
        // Mantener la foto anterior si no se sube una nueva
        client.setFoto(clientExistente.getFoto());
    }

    clientService.actualizarClient(client);
    return "redirect:/clientes";
}
    @GetMapping("/clientes/eliminar/{id}")
    public String eliminarClient(@PathVariable int id) throws IOException{
        Client client = clientService.getforID(id);
        if (client!=null && client.getFoto()!=null){
            Path archivo = Paths.get(uploadDir).resolve(client.getFoto());
            Files.deleteIfExists(archivo);
        }
        clientService.eliminarClient(id);
        return "redirect:/clientes";
    }
    
}