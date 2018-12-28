package es.ucm.fdi.tp.launcher;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

import es.ucm.fdi.tp.launcher.Main.Main;

public class MainTest {
	//METODOS.
	@Test
	public void tooMuchArguments() throws IOException {
		//Se ejecuta correctamente si se meten mas argumentos de los esperados.
		//Lo que quiere decir es que salta la excepcion y
		//se controla.
		String[] argsAux = {"ttt", "RAnd", "Smat", "consOLE"};
		Main.main(argsAux);
	}
	//----------------------------------------------------------------------------
	@Test
	public void lessArguments() throws IOException {
		//Se ejecuta correctamente si se meten menos argumentos de los esperados.
		//Lo que quiere decir es que salta la excepcion y
		//se controla.
		String[] argsAux = {"was", "rand"};
		Main.main(argsAux);
	}
	//----------------------------------------------------------------------------
	@Test
	public void wrongGameName() throws IOException {
		//Se ejecuta correctamente si el juego introducido no es correcto.
		//Lo que quiere decir es que salta la excepcion y
		//se controla.
		String[] argsAux = {"juegoNoValido", "console", "console"};
		final ByteArrayOutputStream auxLine = new ByteArrayOutputStream();
		System.setOut(new PrintStream(auxLine));
		Main.main(argsAux);
		assertEquals("Expected game to be ttt or was" + System.getProperty("line.separator"), auxLine.toString());
	}
}
