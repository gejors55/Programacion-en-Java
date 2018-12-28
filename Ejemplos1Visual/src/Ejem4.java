import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.EventQueue;

public class Ejem4 extends JFrame {

	public Ejem4() {
		super("Mi primera ventana - Ejemplo 4");
		
		this.setSize(700, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton boton = new JButton("Pulsa para saludar");
		JButton otroBoton = new JButton("Ahora salimos!");
		
		//this.getContentPane().setLayout(new FlowLayout()); //SALE CENTRADO
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		//this.getContentPane().setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		this.getContentPane().add(new JLabel("Ventana de saludo"));
		this.getContentPane().add(boton);
		this.getContentPane().add(new JLabel("otra etiqueta"));
		this.getContentPane().add(otroBoton);

		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("Hola Mundo!");
			}});

		otroBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}});
	}

	public static void main(String []args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				final Ejem4 v = new Ejem4();
				v.setVisible(true);
			}});
	}
}

