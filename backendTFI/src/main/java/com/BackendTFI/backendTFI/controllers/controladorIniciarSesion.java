package com.BackendTFI.backendTFI.controllers;

import com.BackendTFI.backendTFI.Dao.UsuarioDao;
import com.BackendTFI.backendTFI.Models.Usuario;
import com.BackendTFI.backendTFI.utils.JWTUtil;
import com.BackendTFI.backendTFI.utils.Sesion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class controladorIniciarSesion {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private Sesion sesion;

    @RequestMapping (value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){

        Usuario usuariologueado = usuarioDao.verificarUsuario(usuario);

        if( usuariologueado != null){
            String tokenJWT = jwtUtil.create(String.valueOf(usuariologueado.getId()), usuariologueado.getEmail());
            sesion.setUsuario(usuariologueado.getEmail());
            System.out.println(sesion.getUsuario());
            return tokenJWT;


        }
        return "Fail";
    }
}
