package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class LoginController implements Initializable{ // �ʱ�ȭ
	// ��Ʈ�Ѹ� ����
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		labelconfirm.setText("");
	}
	
	// ���� Ŭ������ ��üȭ
	private static LoginController instance;
	
	public LoginController() {
		// �׳� this�� ���� ���� Ŭ���� ��ü�� �޸𸮸� ���� �ִ°���
		instance = this;
	}
	
	public static LoginController getInstance() {
		return instance;
	}
	
    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private BorderPane BoardPane;

    @FXML
    private Label btnSignup;

    @FXML
    private Button btnlogin;

    @FXML
    private Label findid;

    @FXML
    private Label findpw;

    @FXML
    private Label labelconfirm;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private TextField txtid;

    @FXML
    private PasswordField txtpw;


    @FXML
    void findid(MouseEvent event) {
    	System.out.println("dddd");
    	loadpage("test2_findid");
    }

    @FXML
    void findpw(MouseEvent event) {

    }

    @FXML
    void onaction(ActionEvent event) {
      	// txt�� �Էµ� ���̵�� ��й�ȣ�� ������ ���Ͽ� ������ �α��� �ƴϸ� ���� 
    	if(txtid.getText().equals("admin")) {
    		labelconfirm.setText("�α��μ���");
    	} else {
    		labelconfirm.setText("������ ȸ�� ������ ����");
    	}
    }
  


    @FXML
    void Signup(MouseEvent event) { 
    	loadpage("test2_signup"); 
    }

	
	// board ���� ����
	public void loadpage(String page)  {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/Day1/" + page + ".fxml"));
			BoardPane.setCenter(parent);
		}catch (Exception e) {
			
		}
	}
}
