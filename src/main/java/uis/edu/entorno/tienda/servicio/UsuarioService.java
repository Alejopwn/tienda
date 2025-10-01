package uis.edu.entorno.tienda.servicio;
import jakarta.transaction.Transactional;
import uis.edu.entorno.tienda.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.edu.entorno.tienda.repositorio.UsuarioRepositorio;

import java.util.List;


@Service
@Transactional
public class UsuarioService implements IUsuarioService{

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Override
    public List<Usuario> getUsuarios(){
        return usuarioRepositorio.findAll();
    }

    @Override
    public Usuario nuevoUsuario(Usuario usuario){
        return usuarioRepositorio.save(usuario);
    }

    @Override
    public Usuario buscarUsuario(Long id){
        Usuario usuario = null;
        usuario =  usuarioRepositorio.findById(id).orElse(null);
        if(usuario == null){
            return null;
        }
        return usuario;
    }

    @Override
    public int borrarUsuario(Long id){
        usuarioRepositorio.deleteById(id);
        return 1;
    }
}
