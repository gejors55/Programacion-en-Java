import javax.swing.*;
import java.awt.event.*;
import java.awt.EventQueue;

public class Ejemplo3b extends JFrame {

	public Ejemplo3b() {
		super("Mi primera ventana - Ejemplo 3b");
		this.setSize(320, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton boton = new JButton("Pulsa para saludar");
		boton.addActionListener(new MiActionListener());
		this.getContentPane().add(boton);

	}

	public class MiActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("Hola Mundo!");
			int modifiers = event.getModifiers();

			if ((modifiers & ActionEvent.ALT_MASK) != 0)
				System.out.println("Has pulsado Alt");
			if ((modifiers & ActionEvent.CTRL_MASK) != 0)
				System.out.println("Has pulsado Ctrl");
			if ((modifiers & ActionEvent.SHIFT_MASK) != 0)
				System.out.println("Has pulsado mayusculas");
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				final Ejemplo3b v = new Ejemplo3b();
				v.setVisible(true);
			}
		});
	}
}
