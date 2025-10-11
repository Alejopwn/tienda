package uis.edu.entorno.tienda.modelo.dto;

public class AuthRequest {
    private String nombreUsuario;
    private String password;
    // getters/setters


    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}