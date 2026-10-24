package com.BackendTFI.backendTFI.Dao;


import com.BackendTFI.backendTFI.Models.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuario();

    void eliminarUsuario(Long id);

    void registrarUsuario(Usuario usuario);

    public Usuario verificarUsuario(Usuario usuario);

    void modificarUsuario(Long id);
}


