package uis.edu.entorno.tienda.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uis.edu.entorno.tienda.modelo.Proveedor;
import uis.edu.entorno.tienda.repositorio.ProveedorRepositorio;

import java.util.List;
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    // Listar todos los proveedores
    @GetMapping("/list")
    public List<Proveedor> listarProveedores() {
        return proveedorRepositorio.findAll();
    }

    // Buscar proveedor por ID
    @GetMapping("/list/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Integer id) {
        return proveedorRepositorio.findById(id)
                .<ResponseEntity<Object>>map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>("Proveedor no encontrado", HttpStatus.NOT_FOUND));
    }


    // Agregar un nuevo proveedor
    @PostMapping("/")
    public ResponseEntity<?> agregar(@RequestBody Proveedor proveedor) {
        if (proveedor.getNombre() == null || proveedor.getNombre().isEmpty()) {
            return new ResponseEntity<>("El nombre del proveedor es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (proveedor.getNit() == null || proveedor.getNit().isEmpty()) {
            return new ResponseEntity<>("El NIT del proveedor es obligatorio", HttpStatus.BAD_REQUEST);
        }
        Proveedor nuevo = proveedorRepositorio.save(proveedor);
        return new ResponseEntity<>(nuevo, HttpStatus.OK);
    }

    // Actualizar proveedor existente
    @PutMapping("/")
    public ResponseEntity<?> editar(@RequestBody Proveedor proveedor) {
        return proveedorRepositorio.findById(proveedor.getId())
                .map(p -> {
                    if (proveedor.getNombre() == null || proveedor.getNombre().isEmpty()) {
                        return new ResponseEntity<>("El nombre del proveedor es obligatorio", HttpStatus.BAD_REQUEST);
                    }
                    if (proveedor.getNit() == null || proveedor.getNit().isEmpty()) {
                        return new ResponseEntity<>("El NIT del proveedor es obligatorio", HttpStatus.BAD_REQUEST);
                    }
                    p.setNombre(proveedor.getNombre());
                    p.setDireccion(proveedor.getDireccion());
                    p.setCiudad(proveedor.getCiudad());
                    p.setTelefono(proveedor.getTelefono());
                    p.setNit(proveedor.getNit());
                    proveedorRepositorio.save(p);
                    return new ResponseEntity<>(p, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>("Proveedor no encontrado", HttpStatus.NOT_FOUND));
    }

    // Eliminar proveedor
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        if (!proveedorRepositorio.existsById(id)) {
            return new ResponseEntity<>("Proveedor no encontrado", HttpStatus.NOT_FOUND);
        }
        proveedorRepositorio.deleteById(id);
        return new ResponseEntity<>("Proveedor eliminado", HttpStatus.OK);
    }
}
