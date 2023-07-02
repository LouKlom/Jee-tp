package org.eclipse.jakarta.model;
import jakarta.persistence.*;

@Entity
@NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")
public class Client {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique = true, length = 20, nullable = false)
    private String username;

    private String password;

    private String token;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}