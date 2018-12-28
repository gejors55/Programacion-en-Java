import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;
import java.awt.EventQueue;

public class ejChangeEvent extends JFrame {

	public ejChangeEvent() {
		super("Mi primera ventana - Ejemplo 3b");
		this.setSize(320, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton boton = new JButton("Pulsa para saludar");
		boton.addActionListener(new MiActionListener());
		boton.addChangeListener(new miChangeListener());
		this.getContentPane().add(boton);
	}

	public class MiActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			((JButton) event.getSource()).setText("pulsado");
		}
	}

	public class miChangeListener implements ChangeListener {

		public void stateChanged(ChangeEvent e) {
			System.out.println("Estais haciendo cambios en el estado!");
		}

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ejChangeEvent v = new ejChangeEvent();
				v.setVisible(true);
			}
		});
	}
}
