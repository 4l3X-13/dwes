package daw.anz.helloworld.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private String name;
    private String email;

    // Constructor explícito (evita fallos de Lombok al empaquetar con Maven)
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Constructor vacío por defecto
    public User() {
    }
}