package com.BackendTFI.backendTFI.controllers;


import com.BackendTFI.backendTFI.Models.Usuario;
import com.BackendTFI.backendTFI.Dao.UsuarioDao;
import com.BackendTFI.backendTFI.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController{

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuario/{id}")
    public Usuario getUsuario(@PathVariable long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Matias");
        usuario.setApellido("Piovesan");
        usuario.setEmail("piovesanmatias@gmail.com");
        usuario.setPassword("1234");


        return usuario;

    }


    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List <Usuario> getUsuarios(@RequestHeader (value = "Authorization") String token) {
        if(!validarToken(token)){return null;}
        return usuarioDao.getUsuario();
    }





    private boolean validarToken(String token){
        String usuarioId= jwtUtil.getKey(token);
        return usuarioId != null;
    }








    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuarios(@RequestBody Usuario usuario ) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrarUsuario(usuario);
    }






    @RequestMapping(value = "api/usuarios/modificar/{id}", method = RequestMethod.PUT)
    public void modificarUsuario(@RequestHeader (value = "Authorization") String token, @PathVariable Long id){
        if (!validarToken(token)){return;}

        usuarioDao.modificarUsuario(id);

    }


    @RequestMapping(value = "api/usuarios/eliminar/{id}", method = RequestMethod.DELETE)
    public void eliminarUsuario(@RequestHeader (value = "Authorization") String token ,@PathVariable Long id){
        if(!validarToken(token)){return;}
        usuarioDao.eliminarUsuario(id);

    }


    @RequestMapping(value = "api/usuario/search")
    public Usuario bucasrUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Matias");
        usuario.setApellido("Piovesan");
        usuario.setEmail("piovesanmatias@gmail.com");
        usuario.setPassword("1234");


        return usuario;

    }



}
