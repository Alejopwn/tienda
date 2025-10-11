package uis.edu.entorno.tienda.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.edu.entorno.tienda.modelo.Producto;
import uis.edu.entorno.tienda.repositorio.ProductoRepositorio;
import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Override
    public List<Producto> getProductos() {
        return productoRepositorio.findAll();
    }

    @Override
    public Producto nuevoProducto(Producto producto) {
        if (producto != null) {
            return productoRepositorio.save(producto);
        }
        return null;
    }

    @Override
    public Producto buscarProducto(Integer id) {
        return productoRepositorio.findById(id).orElse(null);
    }

    @Override
    public void borrarProducto(Integer id) {
        productoRepositorio.deleteById(id);
    }
}