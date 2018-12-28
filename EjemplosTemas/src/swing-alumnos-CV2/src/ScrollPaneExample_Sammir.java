import java.awt.Dimension;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;


public class ScrollPaneExample_Sammir extends JFrame {

	public ScrollPaneExample_Sammir() {
		super("Scroll Pane with JPanel");
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel(new BorderLayout());

		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

		addButton("Holaa", p);
		addButton("Holaaa", p);
		addButton("Holaaaa", p);
		addButton("Holaaaaa", p);
		addButton("Holaaaaaaaaaaaaaaaaaaaaaa", p);
		addButton("Holaaaaaaaaaaaaaaaaaaaaaa", p);
		addButton("Holaaaaaaaaaaaaaaaaaaaaaa", p);
		addButton("Holaaaaaaaaaaaaaaaaaaaaaa", p);

		
		JScrollPane area = new JScrollPane(p);
		area.setPreferredSize(new Dimension(100, 100));
		mainPanel.add(area);
		

		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setSize(700, 300);
		this.pack();
		this.setVisible(true);
	}

	private void addButton(String text, JPanel container) {
		JButton button = new JButton(text);
		// try LEFT or RIGHT instead of CENTER
		//probarlo con esto y sin esto
		//button.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(button);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ScrollPaneExample_Sammir();
			}
		});
	}
}
