package uis.edu.entorno.tienda.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uis.edu.entorno.tienda.modelo.Producto;
import uis.edu.entorno.tienda.servicio.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    // Listar todos los productos
    @GetMapping("/list")
    public List<Producto> cargarProductos() {
        return productoService.getProductos();
    }

    // Buscar producto por ID
    @GetMapping("/list/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        Producto producto = productoService.buscarProducto(id);
        if (producto == null) {
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    // Agregar un producto
    @PostMapping("/")
    public ResponseEntity<?> agregar(@RequestBody Producto producto) {
        if (producto.getIdProveedor() == null) {
            return new ResponseEntity<>("El proveedor es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            return new ResponseEntity<>("El nombre del producto es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (producto.getPrecioCompra() == null || producto.getPrecioCompra() <= 0) {
            return new ResponseEntity<>("El precio de compra debe ser mayor a 0", HttpStatus.BAD_REQUEST);
        }
        if (producto.getPrecioVenta() == null || producto.getPrecioVenta() <= 0) {
            return new ResponseEntity<>("El precio de venta debe ser mayor a 0", HttpStatus.BAD_REQUEST);
        }
        if (producto.getIvaCompra() == null || producto.getIvaCompra() < 0) {
            return new ResponseEntity<>("El IVA de compra no puede ser negativo", HttpStatus.BAD_REQUEST);
        }
        Producto obj = productoService.nuevoProducto(producto);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    // Actualizar un producto
    @PutMapping("/")
    public ResponseEntity<?> editar(@RequestBody Producto producto) {
        Producto obj = productoService.buscarProducto(producto.getId());
        if (obj == null) {
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        }
        if (producto.getIdProveedor() == null) {
            return new ResponseEntity<>("El proveedor es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            return new ResponseEntity<>("El nombre del producto es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (producto.getPrecioCompra() == null || producto.getPrecioCompra() <= 0) {
            return new ResponseEntity<>("El precio de compra debe ser mayor a 0", HttpStatus.BAD_REQUEST);
        }
        if (producto.getPrecioVenta() == null || producto.getPrecioVenta() <= 0) {
            return new ResponseEntity<>("El precio de venta debe ser mayor a 0", HttpStatus.BAD_REQUEST);
        }
        if (producto.getIvaCompra() == null || producto.getIvaCompra() < 0) {
            return new ResponseEntity<>("El IVA de compra no puede ser negativo", HttpStatus.BAD_REQUEST);
        }
        obj.setNombre(producto.getNombre());
        obj.setIvaCompra(producto.getIvaCompra());
        obj.setPrecioCompra(producto.getPrecioCompra());
        obj.setPrecioVenta(producto.getPrecioVenta());
        obj.setIdProveedor(producto.getIdProveedor());
        productoService.nuevoProducto(obj);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        Producto obj = productoService.buscarProducto(id);
        if (obj == null) {
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        }
        productoService.borrarProducto(id);
        return new ResponseEntity<>("Producto eliminado", HttpStatus.OK);
    }
}