package uis.edu.entorno.tienda.servicio;

import java.util.List;
import uis.edu.entorno.tienda.modelo.Usuario;

public  interface IUsuarioService {

    List<Usuario> getUsuarios();

    Usuario nuevoUsuario(Usuario usuario);

    Usuario buscarUsuario(Long id);

    int borrarUsuario(Long id);

}
