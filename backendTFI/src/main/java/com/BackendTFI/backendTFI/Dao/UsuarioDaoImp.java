package com.BackendTFI.backendTFI.Dao;

import com.BackendTFI.backendTFI.Models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Usuario> getUsuario() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();

    }

    @Override
    public void eliminarUsuario(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);

    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        entityManager.merge(usuario);

    }


    /*
    public Usuario verificarUsuario(Usuario usuario){

        String query = "FROM Usuario WHERE email= :email";

        List <Usuario> lista = entityManager.createQuery(query).setParameter("email", usuario.getEmail()).getResultList();

        if(lista.isEmpty()){
            return null;
        }

        String passwordHashes = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);

        if ( argon2.verify(passwordHashes, usuario.getPassword())){
            return lista.get(0);
        }else {
            return  null;
        }

    }

     */
/*
    public Usuario verificarUsuario(Usuario usuario){

        String query = "FROM Usuario WHERE email= :email";

        try {
            Usuario usuarioEncontrado = (Usuario) entityManager.createQuery(query)
                    .setParameter("email", usuario.getEmail())
                    .getSingleResult();

            String passwordHashes = usuarioEncontrado.getPassword();

            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);

            if (argon2.verify(passwordHashes, usuario.getPassword())) {
                return usuarioEncontrado;
            } else {
                return null;
            }
        } catch (NoResultException | NonUniqueResultException ex) {

            return null;
        }
    }
*/





    public Usuario buscarUsuarioPorEmail(String email) {
        String query = "FROM Usuario WHERE email= :email";

        try {
            return (Usuario) entityManager.createQuery(query)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            return null;
        }
    }

    public boolean verificarContraseña(Usuario usuario, String contraseña) {
        String passwordHashes = usuario.getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);

        return argon2.verify(passwordHashes, contraseña);
    }

    public Usuario verificarUsuario(Usuario usuario) {
        Usuario usuarioEncontrado = buscarUsuarioPorEmail(usuario.getEmail());

        if (usuarioEncontrado != null && verificarContraseña(usuarioEncontrado, usuario.getPassword())) {
            return usuarioEncontrado;
        } else {
            return null;
        }
    }



    @Override
    public void modificarUsuario(Long id) {
        Usuario usuarioaModificar = entityManager.find(Usuario.class, id);



    }

}
