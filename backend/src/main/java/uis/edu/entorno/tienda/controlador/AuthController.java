package uis.edu.entorno.tienda.controlador;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;          // ✅
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uis.edu.entorno.tienda.Seguridad.JwtUtil;
import uis.edu.entorno.tienda.modelo.dto.AuthRequest;
import uis.edu.entorno.tienda.modelo.dto.AuthResponse;

// controlador/AuthController.java
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                request.getNombreUsuario(), request.getPassword());
        authManager.authenticate(auth); // lanza excepción si credenciales inválidas

        String token = jwtUtil.generateToken(request.getNombreUsuario());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
