package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServer {
	int port = 9876;

	ServerSocket serverSocket;

	public SocketServer() {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println(port + "포트 준비");

			int cnt = 0;

			while (true) {
				Socket s = serverSocket.accept();
				System.out.println("[===서버: 클라이언트와 연결===]");

				// 읽어올 때 코드
				InputStream is = s.getInputStream();
				Scanner sc = new Scanner(is); // Scanner: 2byte처리.
				System.out.println("[===서버: 클라이언트로부터 받은 인사말===" + sc.nextLine() + "]");

				// 서버에서 클라이언트로 응답을 전송한다.
				OutputStream o = s.getOutputStream();
				PrintStream p = new PrintStream(o);
				p.print("클라이언트님도 안녕하신가요?\n"); // 보낼 메세지
				System.out.println("[===서버: 클라이언트로 인사말 출력===]");
				p.flush(); // 버퍼의 데이터를 한 번에 쏟아낸다.

				// 이 경우 서버 <> 클라이언트 간 연결만 해제. 서버는 아직 살아있다.
				s.close();
				System.out.println("[===서버: " + s.getInetAddress().getHostAddress() + "클라이언트와 연결 해제===]");

				// 서버는 무한 실행되어야 하지만, 구현이므로 5번 요청처리시 종료한다.
				if (cnt > 5) {
					serverSocket.close(); // 서버를 죽인다.
					System.out.println("[===서버: 서버 종료===]");

				}
				cnt += 1;
			}

		} catch (IOException err) {
			System.out.println("소켓 생성 실패 > " + err);
		}
	}
}