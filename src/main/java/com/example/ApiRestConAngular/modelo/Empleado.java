package com.example.ApiRestConAngular.modelo;

import javax.persistence.*;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autgenere el id
    private Long id;

    @Column(name = "nombre", length = 60, nullable = false) //no aceptara valores nulos
    private String nombre;
    @Column(name = "apellido", length = 60, nullable = false) //no aceptara valores nulos
    private String apellido;
    @Column(name = "email", length = 60, nullable = false, unique = true) //no aceptara valores nulos
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
