package es.ucm.fdi.tp.launcher;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.Test;

/**
 * comprueba que: 
 * 1) proporcionar menos de 3 argumentos ó demasiados argumentos
 *    (más jugadores de los que acepta el juego) resulta en un error. 
 * 2) proporcionar un juego inválido como primer argumento resulta en un error.
 * 
 * @author Jorge González Soria y Lázaro Clemen Palafox
 *
 */
public class MainTest {
	/**
	 * demasiados argumentos
	 * 
	 * @throws IOException
	 */
	@Test
	public void testMain1() throws IOException {
		String[] args = { "was", "rand", "smart", "console" };
		MainPr4.main(args);
	}

	/**
	 * proporcionar menos de 3 argumentos
	 * 
	 * @throws IOException
	 */
	@Test
	public void testMain2() throws IOException {
		String[] args = { "was", "smart" };
		MainPr4.main(args);
	}

	/**
	 * proporcionar un juego inválido
	 */
	@Test
	public void testMain3() {
		String[] args = { "sdlgd", "console", "console" };
		final ByteArrayOutputStream line = new ByteArrayOutputStream();
		System.setOut(new PrintStream(line));
		MainPr4.main(args);
		assertEquals("Error: modo juego '" + args[0] + "' no definido."
				+ System.getProperty("line.separator"), line.toString());
	}
}
