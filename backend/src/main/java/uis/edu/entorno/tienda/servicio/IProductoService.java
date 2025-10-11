package uis.edu.entorno.tienda.servicio;

import uis.edu.entorno.tienda.modelo.Producto;
import java.util.List;

public interface IProductoService {
    List<Producto> getProductos();
    Producto nuevoProducto(Producto producto);
    Producto buscarProducto(Integer id);
    void borrarProducto(Integer id);
}