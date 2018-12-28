import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class Vista extends JFrame implements ObservadorModelo {
	private Controlador control;
	
	private  JTextField txtResultado;
	
	public Vista(Controlador c) {
		super("MVC");
		control = c;
		
		this.getContentPane().setLayout(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600,200);
		
		
		final JTextField txtNumero = new JTextField("");
		txtNumero.setPreferredSize(new Dimension(80,30));
		this.getContentPane().add(txtNumero);
		
		
		txtResultado = new JTextField();
		txtResultado.setPreferredSize(new Dimension(80,30));
		//para que no me deje escribir en este JTextField
		txtResultado.setEditable(false);
		this.getContentPane().add(txtResultado);
		
		JButton btnCalcular = new JButton("Calcula");
		this.getContentPane().add(btnCalcular);
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(txtNumero.getText());
				control.calcular(txtNumero.getText());
			}
		});
		//aqu� registramos la vista como observador del modelo
		//a trav�s de los m�todos del controlador
		control.addObservador(this);
	}

	//por implementar la interfaz debe ofrecer este m�todo
	public void actualizar(double dato) {
		String str = Double.toString(dato);
		txtResultado.setText(str);
	}
}
