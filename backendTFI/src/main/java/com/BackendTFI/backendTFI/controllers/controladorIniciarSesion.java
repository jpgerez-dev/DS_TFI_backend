package com.BackendTFI.backendTFI.controllers;

import com.BackendTFI.backendTFI.Dao.UsuarioDao;
import com.BackendTFI.backendTFI.Models.Usuario;
import com.BackendTFI.backendTFI.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class controladorIniciarSesion {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {

        Usuario usuarioLogueado = usuarioDao.verificarUsuario(usuario);

        if (usuarioLogueado == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas");
        }
        return jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
    }
}
