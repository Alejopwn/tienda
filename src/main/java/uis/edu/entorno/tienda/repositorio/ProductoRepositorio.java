package uis.edu.entorno.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import uis.edu.entorno.tienda.modelo.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
}