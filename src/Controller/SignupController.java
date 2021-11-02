package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.MemberDAO;
import domain.Member;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SignupController implements Initializable {
   
	
	   @FXML
	    private AnchorPane SignupPane;

	    @FXML
	    private Label btnBack;

	    @FXML
	    private Button btnsignup;

	    @FXML
	    private Label signupconfirm;

	    @FXML
	    private TextField signupemailtxt;

	    @FXML
	    private TextField signupidtxt;

	    @FXML
	    private TextField signupname;

	    @FXML
	    private PasswordField signuppwtxt1;

	    @FXML
	    private PasswordField signuppwtxt2;

	    @FXML
	    void Back(MouseEvent event) {
	    	LoginController.getInstance().loadpage("test2");
	    }

	    @FXML
	    void signup(ActionEvent event) {
	    	// ��Ʈ�� ȣ��� -> fx:id ȣ��
	    	
	    	//-----------------------------------------------------------------------------------------------------
	    	// 1. ��ȿ�� �˻�
	    	// ���̵� üũ
	    	if(signupidtxt.getText().length() <= 4 || signupidtxt.getText().length() > 13) {
	    		signupconfirm.setText("���̵�� 4���� �̻�");
	    		return;
	    	}
	    	
	    	// �н����� ���� üũ
	    	if(signuppwtxt1.getText().length() < 4 || signuppwtxt1.getText().length() > 8) {
	    		signupconfirm.setText("��й�ȣ�� 4~8�ڸ�");
	    	}
	    	
	    	// �н����� ���� üũ
	    	if(!signuppwtxt1.getText().equals(signuppwtxt2.getText())) {
	    		signupconfirm.setText("�н����尡 ���� ����");
	    	}
	    	
	    	// �̸� ���� üũ
	    	if(signupname.getText().length() <= 2 && signupname.getText().length() > 4) {
	    		signupconfirm.setText("�̸��� 2~4����");
	    		return;
	    	}
	    	
	    	// �̸��� ���� üũ
	    	if(signupemailtxt.getText().length() < 5 && !signupemailtxt.getText().contains("@")) {
	    		signupconfirm.setText("�̸����� @�� �־����");
	    	}
	    	
	    	//-----------------------------------------------------------------------------------------------------
	    	
	    	// 2. �ߺ�üũ
	    	boolean resultIdCheck = MemberDAO.getMemberDAO().idCheck(signupidtxt.getText());
	    	if(resultIdCheck) {
	    		signupconfirm.setText("������� ���̵�");
	    		return;
	    	}
	    	
	    	// 3. ��üȭ
	    	Member member = new Member(signupidtxt.getText(), signuppwtxt1.getText(), signupname.getText(), signupemailtxt.getText()); 
	    	
	    	// 4. ���� Ȥ�� dbó��
	    	boolean result = MemberDAO.getMemberDAO().signup(member);
	    	if(result) {
	    	
	    		// 5. �޼��� â�� ���� ������ ��ȯ�ʿ�
		    	// ��� �޼��� ��ü ����
		    	Alert alert = new Alert(AlertType.INFORMATION);
		    	alert.setContentText("Music Producing ȸ������ ���� [����Ʈ 1000 ����]"); // �޼��� â�� �ߴ� ��
		    	alert.setHeaderText("ȸ�����Լ���"); 
		    	alert.setTitle("�˸�");
		    	alert.showAndWait(); // �޼����� ���� �Է½ñ��� ��ٸ�
		    	LoginController.getInstance().loadpage("test2");
		    	
	    	} else {
	    		System.out.println("DB ����");
	    	}
	    	
	    	
	    
	    }
	    
	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    	signupconfirm.setText("");
	    }
}
