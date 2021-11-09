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
	// 1. Ŭ���̾�Ʈ ���� ����
	Socket socket;
	
	// 2. Ŭ���̾�Ʈ ���� �޼ҵ�
	public void clientStart() {
		// ��Ƽ������ - ������Ǯx
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
	
	// 3. Ŭ���̾�Ʈ ����޼ҵ�
	public void clientStop() { 
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 4. �޽��� ������ �޼ҵ�
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
	
	// 5. �޽��� �޴� �޼ҵ�
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
	
	// 6. �Է¹�ư ������ ��
	@FXML
	void inputbtnAction(ActionEvent event) {
		send(loginid + " : " + Contenttxt.getText() + "\n");
		
		Contenttxt.setText("");
		Contenttxt.requestFocus();
	}
	
	// 7. ���͸� ������ ��
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
		if(Connectbtn.getText().equals("����")) {
			// Ŭ���̾�Ʈ ����
			clientStart();
			
			// ���� �޽��� ����
			Platform.runLater(() -> Clienttxt.appendText("ä�ù� ����\n"));
			// 3. ��Ʈ�� ���� ����
			Connectbtn.setText("������");
			Contenttxt.setDisable(false);
			Clienttxt.requestFocus(); // ���콺 ������ �̵�
			inputbtn.setDisable(false);
		} else {
			// Ŭ���̾�Ʈ ����
			clientStop();
			
			// ���� �޽��� ����
			Platform.runLater(() -> Clienttxt.appendText("ä�ù� ����\n"));
			// ��Ʈ�� ���� ����
			Connectbtn.setText("����");
			Contenttxt.setDisable(true);
			inputbtn.setDisable(true);
			
		}
	}
}
