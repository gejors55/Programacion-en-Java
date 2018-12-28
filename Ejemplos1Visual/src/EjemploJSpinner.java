import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EjemploJSpinner extends JFrame {

	private JTextField tf;

	private JSpinner spinner;
	int Time0 = 1000;

	public EjemploJSpinner() {
		super("ejemplo: Spinner");
		this.setSize(320, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Creacion del JTextField
		tf = new JTextField(20);

		//spinner = new JSpinner(); si utilizamos este constructor 
		//comenzamos desde 0 e incrementamos uno a uno.
		//Para hacerlo a nuestro gusto utilizar el otro constructor
		//JSpinner(SpinnerModel model). 
		//Constructor: SpinnerNumberModel(int value, int minimum, int maximum, int stepSize) 
		
		// 5000 es el máximo, 0 es el mímimo y el incremento es 500 y el valor
		// inicial en ppio está en Time0
		spinner = new JSpinner(new SpinnerNumberModel(Time0, 0, 5000,
				500));
		
		
		// Nos suscribimos a cambios en el JSpinner
		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// Ponemos el valor del JSpinner en el JTextField
				tf.setText(spinner.getValue().toString());
				
			}
		});

		// Creacion de la ventana con los componentes

		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(spinner);
		getContentPane().add(tf);
		pack();
		setVisible(true);
		//setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				final EjemploJSpinner v = new EjemploJSpinner();
				v.setVisible(true);
			}
		});

	}

}