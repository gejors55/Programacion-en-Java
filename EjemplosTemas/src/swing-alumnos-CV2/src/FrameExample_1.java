import javax.swing.*;

public class FrameExample_1 extends JFrame {
	public FrameExample_1() {
		super("Mi primera Ventana");
		initGUI();
	}

	private void initGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 100);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new FrameExample_1();
			}
		});
	}
}
