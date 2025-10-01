package uis.edu.entorno.tienda.repositorio;

import uis.edu.entorno.tienda.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
}
