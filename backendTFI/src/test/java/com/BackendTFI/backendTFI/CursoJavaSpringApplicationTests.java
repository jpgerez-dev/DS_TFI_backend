package com.BackendTFI.backendTFI;

import com.BackendTFI.backendTFI.Dao.UsuarioDaoImp;
import com.BackendTFI.backendTFI.Models.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CursoJavaSpringApplicationTests {

	@Autowired
	private UsuarioDaoImp usuarioDaoImp;

	private TestEntityManager testEntityManager;

	@Test
	public void testBuscarUsuarioPorEmail() {
		Usuario usuarioPrueba = new Usuario();
		usuarioPrueba.setEmail("usuario_prueba@example.com");
		testEntityManager.persist(usuarioPrueba);
		testEntityManager.flush();


		Usuario usuarioEncontrado = usuarioDaoImp.buscarUsuarioPorEmail("usuario_prueba@example.com");

		assertNotNull(usuarioEncontrado);
		assertEquals("usuario_prueba@example.com", usuarioEncontrado.getEmail());
	}






	@Test
	public void testVerificarUsuario() {

		Usuario usuarioPrueba = new Usuario();
		usuarioPrueba.setEmail("usuario_prueba@example.com");
		usuarioPrueba.setPassword("password_hash");
		testEntityManager.persist(usuarioPrueba);
		testEntityManager.flush();


		Usuario usuarioVerificado = usuarioDaoImp.verificarUsuario(usuarioPrueba);


		assertNotNull(usuarioVerificado);
		assertEquals("usuario_prueba@example.com", usuarioVerificado.getEmail());


		usuarioPrueba.setPassword("contraseña_incorrecta");
		usuarioVerificado = usuarioDaoImp.verificarUsuario(usuarioPrueba);
		assertNull(usuarioVerificado);
	}

}
