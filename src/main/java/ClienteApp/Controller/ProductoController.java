/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClienteApp.Controller;

import ClienteApp.Model.Producto;
import ClienteApp.Service.ProductoService;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Value("${app.upload.dir}")
    private String uploadDir;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoService.listar());
        return "productosHTML";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "productoForm";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto, @RequestParam("archivo") MultipartFile archivo) throws IOException {
        if (!archivo.isEmpty()) {
            String nombreImagen = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
            Path path = Paths.get(uploadDir, nombreImagen);
            Files.copy(archivo.getInputStream(), path);
            producto.setFoto(nombreImagen);
        }
        productoService.guardar(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        model.addAttribute("producto", productoService.buscarPorId(id));
        return "productoForm";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Producto producto, @RequestParam("archivo") MultipartFile archivo) throws IOException {
        Producto actual = productoService.buscarPorId(producto.getId());
        if (!archivo.isEmpty()) {
            String nombreImagen = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
            Path path = Paths.get(uploadDir, nombreImagen);
            Files.copy(archivo.getInputStream(), path);
            producto.setFoto(nombreImagen);
        } else {
            producto.setFoto(actual.getFoto());
        }
        productoService.actualizar(producto);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        // 1. Buscamos el producto para saber cómo se llama su foto
        Producto producto = productoService.buscarPorId(id);
        
        if (producto != null && producto.getFoto() != null) {
            try {
                // 2. Construimos la ruta completa al archivo
                Path path = Paths.get(uploadDir).resolve(producto.getFoto()).toAbsolutePath();
                File archivo = path.toFile();
                
                // 3. Intentamos borrar el archivo físico
                if (archivo.exists()) {
                    if (archivo.delete()) {
                        System.out.println("✅ Archivo borrado: " + producto.getFoto());
                    } else {
                        System.err.println("❌ No se pudo borrar el archivo: " + producto.getFoto());
                    }
                }
            } catch (Exception e) {
                System.err.println("❌ Error al intentar eliminar la foto del disco");
                e.printStackTrace();
            }
        }

        // 4. Finalmente, borramos el registro de la base de datos
        productoService.eliminar(id);
        
        return "redirect:/productos";
    }
}
