package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChattingController implements Initializable {
	// 1. 클라이언트 소켓 선언
	Socket socket;
	
	// 2. 클라이언트 시작 메소드
	public void clientStart() {
		// 멀티스레드 - 스레드풀x
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {

					socket = new Socket("127.0.0.1", 1234);
					receive();
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		};
		thread.start();
	}
	
	// 3. 클라이언트 종료메소드
	public void clientStop() { 
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 4. 메시지 보내기 메소드
	public void send(String msg) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					OutputStream outputStream = socket.getOutputStream();
					outputStream.write(msg.getBytes());
					outputStream.flush();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		};
		thread.start();
	}
	
	// 5. 메시지 받는 메소드
	public void receive() {
		while(true) {
			try {
				InputStream inputStream = socket.getInputStream();
				byte[] bytes = new byte[1000];
				inputStream.read(bytes);
				String msg = new String(bytes);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	private String loginid = MainPageController.getInstance().getLogId();
	
	// 6. 입력버튼 눌렀을 때
	@FXML
	void inputbtnAction(ActionEvent event) {
		send(loginid + " : " + Contenttxt.getText() + "\n");
		
		Contenttxt.setText("");
		Contenttxt.requestFocus();
	}
	
	// 7. 엔터를 눌렀을 때
	@FXML
	void msgSendEnter(ActionEvent event) {
		send(loginid + " : " + Contenttxt.getText() + "\n");
		
		Contenttxt.setText("");
		Contenttxt.requestFocus();
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Clienttxt.setEditable(false);
		Contenttxt.setDisable(true);
		inputbtn.setDisable(true);
	}
	
	@FXML
	private TextArea Clienttxt;
	
	@FXML
	private TextField Contenttxt;
	
	@FXML
	private Button inputbtn;
	
	@FXML
	private Button Connectbtn;
	
	@FXML
	void ConnectbtnAction(ActionEvent event) {
		if(Connectbtn.getText().equals("접속")) {
			// 클라이언트 실행
			clientStart();
			
			// 접속 메시지 전달
			Platform.runLater(() -> Clienttxt.appendText("채팅방 입장\n"));
			// 3. 컨트롤 내용 변경
			Connectbtn.setText("나가기");
			Contenttxt.setDisable(false);
			Clienttxt.requestFocus(); // 마우스 포인터 이동
			inputbtn.setDisable(false);
		} else {
			// 클라이언트 종료
			clientStop();
			
			// 퇴장 메시지 전달
			Platform.runLater(() -> Clienttxt.appendText("채팅방 퇴장\n"));
			// 컨트롤 내용 변경
			Connectbtn.setText("접속");
			Contenttxt.setDisable(true);
			inputbtn.setDisable(true);
			
		}
	}
}
