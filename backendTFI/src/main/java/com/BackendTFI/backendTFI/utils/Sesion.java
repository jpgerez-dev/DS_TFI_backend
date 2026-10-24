package com.BackendTFI.backendTFI.utils;

import lombok.Getter;
import lombok.Setter;

public class Sesion {
    private static  final Sesion instancia = new Sesion();


    @Getter @Setter
    public String usuario;
    public static Sesion getInstancia() {
        return instancia;
    }


}
