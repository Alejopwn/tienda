package uis.edu.entorno.tienda.controlador;

import uis.edu.entorno.tienda.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uis.edu.entorno.tienda.servicio.UsuarioService;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    /*
            *se inyecta en la interface del servicio para acceder
            a los metodos del negocio
    * */

    @Autowired
    UsuarioService usuarioService;
    // Listar los usuarios
    @GetMapping("/list")
    public List<Usuario> cargarUsuarios(){
        return usuarioService.getUsuarios();
    }

    // Buscar por Id
    @GetMapping("/list/{id}")
    public Usuario buscarPorId(@PathVariable Long id){
        return usuarioService.buscarUsuario(id);
    }

    // Agregar un Usuario
    @PostMapping("/")
    public ResponseEntity<Usuario> agregar(@RequestBody Usuario usuario) {
        Usuario obj = usuarioService.nuevoUsuario(usuario);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    // Actualizar el Usuario
    @PutMapping("/")
    public ResponseEntity<Usuario> editar (@RequestBody Usuario usuario){
        Usuario obj = usuarioService.buscarUsuario(usuario.getId());
        if(obj == null){
            obj.setEmail(usuario.getEmail());
            obj.setIdTipoDocumento(usuario.getIdTipoDocumento());
            obj.setNombre(usuario.getNombre());
            obj.setNombre(usuario.getNombreUsuario());
            obj.setNumeroDocumento(usuario.getNumeroDocumento());
            obj.setPassword(usuario.getPassword());
            usuarioService.nuevoUsuario(obj);
        }else{
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    //Eliminar el usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> eliminar(@PathVariable Long id){
        Usuario obj = usuarioService.buscarUsuario(id);
        if(obj == null){
            usuarioService.borrarUsuario(id);
        }else{
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

}
