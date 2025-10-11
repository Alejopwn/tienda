package uis.edu.entorno.tienda.servicio;

import java.util.List;

import org.springframework.http.ResponseEntity;
import uis.edu.entorno.tienda.modelo.LoginDto;
import uis.edu.entorno.tienda.modelo.Usuario;


public  interface IUsuarioService {
    int login(LoginDto login);
    ResponseEntity<?> ingresar(LoginDto login);

    List<Usuario> getUsuarios();

    Usuario nuevoUsuario(Usuario usuario);

    Usuario buscarUsuario(Long id);


    int borrarUsuario(Long id);

}
