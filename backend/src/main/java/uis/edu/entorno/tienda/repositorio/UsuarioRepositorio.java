package uis.edu.entorno.tienda.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uis.edu.entorno.tienda.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    //JPQL: Se hace la consulta sobre la clase

    // uis.edu.entorno.tienda.repositorio.UsuarioRepositorio
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);


    // Devuelve un conteo (para saber si existe el usuario)
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario AND u.password = :password")
    Integer findByNombreUsuarioAndPassword(@Param("nombreUsuario") String nombreUsuario,
                                           @Param("password") String password);

    // Devuelve el usuario encontrado (para login)
    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario AND u.password = :password")
    Usuario findByNameAndPassword(@Param("nombreUsuario") String nombreUsuario,
                                  @Param("password") String password);

}


