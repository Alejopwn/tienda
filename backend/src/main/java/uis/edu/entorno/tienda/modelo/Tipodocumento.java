package uis.edu.entorno.tienda.modelo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = Tipodocumento.TABLE_NAME)
public class Tipodocumento {

    public static final String TABLE_NAME = "tipodocumento";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tipo")
    private String tipo;

    // Constructor vacío
    public Tipodocumento() {
    }

    // Constructor con parámetros
    public Tipodocumento(long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    // Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
