package uis.edu.entorno.tienda.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uis.edu.entorno.tienda.modelo.Usuario;
import uis.edu.entorno.tienda.repositorio.UsuarioRepositorio;

// servicio/UsuarioDetailsService.java
@Service
public class UsuarioDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepositorio repo;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario u = repo.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("No existe"));
        return User.withUsername(u.getNombreUsuario())
                .password(u.getPassword())   // debe estar encriptada con BCrypt
                .roles("USER")               // o desde BD si lo manejas
                .build();
    }
}
