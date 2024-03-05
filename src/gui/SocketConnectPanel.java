package gui;

import java.awt.Label;
import java.awt.TextField;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import socket.SocketClient;

// socket 연결 패널
public class SocketConnectPanel extends JPanel {

	SocketClient socket = SocketClient.getInstance();

	String ip = "";
	int port = 5331;

	TextField tfIp = null;
	TextField tfPort = null;

	public SocketConnectPanel() {
		Border border = BorderFactory.createTitledBorder("접속");

		Label lbIp = new Label("IP ", Label.RIGHT);
		Label lbPort = new Label("Port ", Label.RIGHT);
		tfIp = new TextField(ip, 50);
		tfPort = new TextField(port + "", 50);
		JButton jbConnect = new JButton("연결");
		JButton jbDisconnect = new JButton("해제");
		jbConnect.addActionListener(e -> connectServerSocket());
		jbDisconnect.addActionListener(e -> disconnectServerSocket());

		lbIp.setBounds(10, 20, 30, 20);
		tfIp.setBounds(50, 20, 200, 20);
		lbPort.setBounds(10, 50, 30, 20);
		tfPort.setBounds(50, 50, 200, 20);
		jbConnect.setBounds(50, 80, 95, 20);
		jbDisconnect.setBounds(155, 80, 95, 20);

		add(lbIp);
		add(lbPort);
		add(tfIp);
		add(tfPort);
		add(jbConnect);
		add(jbDisconnect);

		setBorder(border);
//		setSize(260, 300);
		setBounds(10, 10, 260, 110);
		setLayout(null);
	}

	public void connectServerSocket() {
		if (tfIp != null && tfPort != null && tfIp.getText().length() > 0 && tfPort.getText().length() > 0) {
			socket.setSocketInfo(tfIp.getText(), Integer.parseInt(tfPort.getText()));
			try {
				socket.connect();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			LogPanel.setLog("ip와 port 정보를 입력해주세요");
		}
	}

	public void disconnectServerSocket() {
		if (socket != null) {
			socket.disconnect();
		}
	}
}