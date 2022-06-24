package umu.tds;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Optional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import umu.tds.dominio.CatalogoUsuarios;
import umu.tds.dominio.Usuario;

public class UsuarioTest {

	public static CatalogoUsuarios catalogo;
	
	static HashSet<Usuario> usuarios;

	@BeforeClass
	public static void configuracionTest() throws Exception {
		
		catalogo = CatalogoUsuarios.getUnicaInstancia();

		for (Usuario usuario : catalogo.getUsuarios())
			catalogo.removeUsuario(usuario);
		
		usuarios = new HashSet<>();
		Usuario aux;
		
		aux = new Usuario("nom1", "apell1", "user1@mail.test", "user1", "1234", "23/10/1967");
		aux.setId(13);
		usuarios.add(aux);
		
		aux = new Usuario("nom2", "apell2", "user2@mail.test", "user2", "1234","24/12/1900");
		aux.setId(67);
		usuarios.add(aux);
		
		aux = new Usuario("nom3", "apell3", "user3@mail.test", "user3", "1234", "24/12/1979");
		aux.setId(456);
		usuarios.add(aux);
		
		aux = new Usuario("nom4", "apell4", "user4@mail.test", "user4", "1234","01/10/2010");
		aux.setId(2352);
		usuarios.add(aux);	
	}

	@Before
	public void testAddUsers() {

		for (Usuario usuario : usuarios)
			catalogo.addUsuario(usuario);
		
		testExistUsers();
	}
	
	@Test
	public void testDeleteUsers() {

		Optional<Usuario> usuario1 = usuarios.stream().findAny();
		assertTrue(usuario1.isPresent());
		
		assertTrue(catalogo.removeUsuario(usuario1.get()));
		
		assertNull(catalogo.getUsuario(usuario1.get().getId()));
		
		usuarios.remove(usuario1.get());
		Optional<Usuario> usuario2 = usuarios.stream().findAny();
		usuarios.add(usuario1.get());
		assertTrue(usuario2.isPresent());
		
		assertTrue(catalogo.removeUsuario(usuario2.get()));
		
		assertNull(catalogo.getUsuario(usuario2.get().getId()));
		
		catalogo.addUsuario(usuario1.get());
		
		assertNotNull(catalogo.getUsuario(usuario1.get().getId()));
		assertNull(catalogo.getUsuario(usuario2.get().getId()));
		
		catalogo.addUsuario(usuario2.get());
		
		testExistUsers();
	}
	
	@Test
	public void testExistUsers() {

		for (Usuario usuario : usuarios) {
			assertNotNull(catalogo.getUsuario(usuario.getId()));
			assertNotNull(catalogo.getUsuario(usuario.getNick()));
		}
	}
	
	@Test
	public void testConsultaManual() {
		
		assertNotNull(catalogo.getUsuario(13));
		assertNull(catalogo.getUsuario(14));
		
		assertNotNull(catalogo.getUsuario("user1"));
		assertNull(catalogo.getUsuario("USER1"));
	}

}
