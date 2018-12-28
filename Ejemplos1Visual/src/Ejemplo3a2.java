import javax.swing.*;
import java.awt.event.*;
import java.awt.EventQueue;

public class Ejemplo3a2 extends JFrame {

	public Ejemplo3a2() {
		super("Mi primera ventana - Ejemplo3");
		this.setSize(320, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton boton = new JButton("Pulsa para saludar");
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("Hola Mundo!");
			}
		});
		this.getContentPane().add(boton);
	}

	
	public static void main(String[] args) {
		// ...
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				final Ejemplo3a v = new Ejemplo3a();
				v.setVisible(true);
			}
		});
	}
}
