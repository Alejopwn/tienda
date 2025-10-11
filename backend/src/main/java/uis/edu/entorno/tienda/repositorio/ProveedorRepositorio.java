package uis.edu.entorno.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import uis.edu.entorno.tienda.modelo.Proveedor;

public interface ProveedorRepositorio extends JpaRepository<Proveedor, Integer> {
}
