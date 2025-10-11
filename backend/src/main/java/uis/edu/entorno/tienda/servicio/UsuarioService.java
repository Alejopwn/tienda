package uis.edu.entorno.tienda.servicio;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uis.edu.entorno.tienda.modelo.LoginDto;
import uis.edu.entorno.tienda.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.edu.entorno.tienda.repositorio.UsuarioRepositorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @Override
    public int login(LoginDto usuarioDto){
        int u = usuarioRepositorio.findByNombreUsuarioAndPassword(usuarioDto.getNombreUsuario(), usuarioDto.getPassword());
        return u;

    }

    @Override
    public ResponseEntity<?> ingresar(LoginDto usuarioDto){
        Map<String, Object> response = new HashMap<>();
        Usuario usuario = null;

        try{
            usuario = usuarioRepositorio.findByNameAndPassword(usuarioDto.getNombreUsuario(), usuarioDto.getPassword());
            if(usuario == null){
                response.put("error", null);
                response.put("message", "Alerta:Usuario o Password Incorrecto");
                response.put("statusCode", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else{
                response.put("Usuario", usuario);
                response.put("message", "Datos correctos");
                response.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e){
            response.put("Usuario", null);
            response.put("message", "Ha ocurrido un error");
            response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}



