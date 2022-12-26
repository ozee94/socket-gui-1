package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	public MainFrame() {
		setLayout(new BorderLayout());

		JPanel panel = new JPanel();

		panel.add(new SocketConnectPanel(), BorderLayout.NORTH);
		panel.add(new SendEventPanel(), BorderLayout.CENTER);

		panel.setSize(300, 580);
		panel.setLayout(new BorderLayout());

		add(panel, BorderLayout.CENTER);
		add(new LogPanel(), BorderLayout.EAST);

		setResizable(false);
		setBounds(100, 100, 800, 600);
		setVisible(true);
	}
}