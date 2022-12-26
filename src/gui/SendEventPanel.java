package gui;

import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import socket.SocketClient;

public class SendEventPanel extends JPanel {
	SocketClient socket = SocketClient.getInstance();

	JTextArea ta = null;

	public SendEventPanel() {
		Border border = BorderFactory.createTitledBorder("전문 보내기");

		ta = new JTextArea(
				"101020180913165134119UCP201809131651340000000188T20180913165134화재교통사고127.099238937.4140687경기도 성남시 수정구 대왕판교로 815 판교창조경제밸리 기업지원허브411311150020180913165134user119;");

		System.out.println(ta);

		ta.setBounds(10, 20, 240, 300);
		ta.setLineWrap(true);

		JButton b1 = new JButton("전문 보내기");
		b1.setBounds(150, 330, 100, 20);
		b1.addActionListener(e -> sendData());

		add(ta);
		add(b1);

		setLayout(null);
		setBounds(10, 120, 260, 440);
		setBorder(border);
	}

	public void sendData() {
//		if (ta != null && ta.getText().length() > 0) {
//			System.out.println("sendData" + ta.getText());
//			try {
//				socket.sendData(ta.getText());
//				socket.disconnect();
//			} catch (IOException err) {
//				LogPanel.setLog("[❌] 전문을 보내는 실패했습니다");
//			}
//		} else {
//			LogPanel.setLog("[❌] 전문내용을 입력해주세요");
//		}

		final long timeInterval = 3000;
		Runnable runnable = new Runnable() {
			SocketClient sc = SocketClient.getInstance();

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						sc.setSocketInfo("10.0.100.138", 5331);
						sc.connect();
						sc.sendData(ta.getText());
						sc.disconnect();
						Thread.sleep(timeInterval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();
	}
}
