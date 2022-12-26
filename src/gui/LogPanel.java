package gui;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class LogPanel extends JPanel {

	public static JTextArea ta = null;

	public LogPanel() {
		setLayout(new BorderLayout());

		Border border = BorderFactory.createTitledBorder("로그");

		ta = new JTextArea();
		ta.setBounds(0, 0, 510, 580);
		ta.setLineWrap(true);
		ta.setEditable(false);

		JScrollPane sp = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(sp, BorderLayout.CENTER);

		setBorder(border);
	}

	public static void setLog(String txt) {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String temp = "[" + sdFormat.format(now) + "] " + txt;

		if (ta.getText().length() > 0) {
			ta.setText(ta.getText() + "\n" + temp);
		} else {
			ta.setText(temp);
		}
	}
}
