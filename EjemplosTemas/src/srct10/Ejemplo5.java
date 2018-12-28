import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.EventQueue;

public class Ejemplo5 extends JFrame {

	public Ejemplo5() {
		super("Mi primera ventana - Ejemplo 5");
		this.setSize(700, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.getContentPane().setLayout(new BorderLayout());

		JLabel lnorth = new JLabel("Región norte");
		lnorth.setBackground(Color.BLUE);
		lnorth.setOpaque(true);
		this.getContentPane().add(lnorth, BorderLayout.NORTH);

		JLabel least = new JLabel("región este");
		least.setBackground(Color.GREEN);
		least.setOpaque(true);
		this.getContentPane().add(least, BorderLayout.LINE_END);

		JLabel lsouth = new JLabel("Región sur");
		lsouth.setBackground(Color.RED);
		lsouth.setOpaque(true);
		this.getContentPane().add(lsouth, BorderLayout.SOUTH);

		JLabel lwest = new JLabel("región oeste");
		lwest.setBackground(Color.YELLOW);
		lwest.setOpaque(true);
		this.getContentPane().add(lwest, BorderLayout.WEST);

		JLabel lcenter = new JLabel("región centro");
		lcenter.setBackground(Color.GRAY);
		lcenter.setOpaque(true);
		this.getContentPane().add(lcenter, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				final Ejemplo5 v = new Ejemplo5();
				v.setVisible(true);
			}
		});
	}
}
