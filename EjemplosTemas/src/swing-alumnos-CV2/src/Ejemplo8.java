import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.EventQueue;

public class Ejemplo8 extends JFrame {
	public Ejemplo8() {
		super("Mi ventanita");
		this.setSize(600,200);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		this.getContentPane().add(mainPanel);
		JButton boton1 = new JButton("hola");
		JButton boton2 = new JButton("holaaa");
		JButton boton3 = new JButton("holaaaaa");
		mainPanel.add(boton1);
		mainPanel.add(boton2);
		mainPanel.add(boton3);
		this.pack();

	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				final Ejemplo8 v = new Ejemplo8();
				v.setVisible(true);
			}
		});
	}

}