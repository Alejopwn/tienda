package uis.edu.entorno.tienda.modelo.dto;

public class AuthResponse {

    private String token;

    // 🔹 Constructor vacío (Jackson lo necesita)
    public AuthResponse() {}

    // 🔹 Constructor con parámetro
    public AuthResponse(String token) {
        this.token = token;
    }

    // 🔹 Getter y Setter (Jackson los usa para serializar a JSON)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
