package com.BackendTFI.backendTFI.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "usuarios")
@ToString

public class Usuario
{
    @Getter @Setter @Column(name="id") @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter @Setter @Column(name="nombre")
    private String nombre;
    @Getter @Setter @Column(name="apellido")
    private String apellido;
    @Getter @Setter @Column(name="email")
    private String email;
    @Getter @Setter @Column(name="password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private String password;
    @Getter @Setter @Column(name="telefono")
    private String telefono;


}
