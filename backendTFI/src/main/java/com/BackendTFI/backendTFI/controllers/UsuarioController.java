package com.BackendTFI.backendTFI.controllers;

import com.BackendTFI.backendTFI.Dao.UsuarioDao;
import com.BackendTFI.backendTFI.Models.Usuario;
import com.BackendTFI.backendTFI.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization", required = false) String token) {
        exigirToken(token);
        return usuarioDao.getUsuario();
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuarios(@RequestBody Usuario usuario) {
        if (usuario.getEmail() == null || usuario.getEmail().isBlank()
                || usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Faltan datos");
        }
        if (usuarioDao.buscarUsuarioPorEmail(usuario.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email ya asociado");
        }

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);
        // 3 iteraciones, 64 MB de memoria, 1 hilo
        String hash = argon2.hash(3, 65536, 1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrarUsuario(usuario);
    }

    @RequestMapping(value = "api/usuarios/modificar/{id}", method = RequestMethod.PUT)
    public void modificarUsuario(@RequestHeader(value = "Authorization", required = false) String token,
                                 @PathVariable Long id) {
        exigirToken(token);
        usuarioDao.modificarUsuario(id);
    }

    @RequestMapping(value = "api/usuarios/eliminar/{id}", method = RequestMethod.DELETE)
    public void eliminarUsuario(@RequestHeader(value = "Authorization", required = false) String token,
                                @PathVariable Long id) {
        exigirToken(token);
        usuarioDao.eliminarUsuario(id);
    }

    /** Responde 401 si falta el token o es inválido / está vencido. */
    private void exigirToken(String token) {
        String usuarioId = null;
        if (token != null && !token.isBlank()) {
            try {
                usuarioId = jwtUtil.getKey(token);
            } catch (JwtException | IllegalArgumentException e) {
                usuarioId = null;
            }
        }
        if (usuarioId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token inválido o vencido");
        }
    }
}
