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
	    	// 컨트롤 호출시 -> fx:id 호출
	    	
	    	//-----------------------------------------------------------------------------------------------------
	    	// 1. 유효성 검사
	    	// 아이디 체크
	    	if(signupidtxt.getText().length() <= 4 || signupidtxt.getText().length() > 13) {
	    		signupconfirm.setText("아이디는 4글자 이상");
	    		return;
	    	}
	    	
	    	// 패스워드 길이 체크
	    	if(signuppwtxt1.getText().length() < 4 || signuppwtxt1.getText().length() > 8) {
	    		signupconfirm.setText("비밀번호는 4~8자리");
	    	}
	    	
	    	// 패스워드 동일 체크
	    	if(!signuppwtxt1.getText().equals(signuppwtxt2.getText())) {
	    		signupconfirm.setText("패스워드가 같지 않음");
	    	}
	    	
	    	// 이름 길이 체크
	    	if(signupname.getText().length() <= 2 && signupname.getText().length() > 4) {
	    		signupconfirm.setText("이름은 2~4글자");
	    		return;
	    	}
	    	
	    	// 이메일 길이 체크
	    	if(signupemailtxt.getText().length() < 5 && !signupemailtxt.getText().contains("@")) {
	    		signupconfirm.setText("이메일은 @를 넣어야함");
	    	}
	    	
	    	//-----------------------------------------------------------------------------------------------------
	    	
	    	// 2. 중복체크
	    	boolean resultIdCheck = MemberDAO.getMemberDAO().idCheck(signupidtxt.getText());
	    	if(resultIdCheck) {
	    		signupconfirm.setText("사용중인 아이디");
	    		return;
	    	}
	    	
	    	// 3. 객체화
	    	Member member = new Member(signupidtxt.getText(), signuppwtxt1.getText(), signupname.getText(), signupemailtxt.getText()); 
	    	
	    	// 4. 파일 혹은 db처리
	    	boolean result = MemberDAO.getMemberDAO().signup(member);
	    	if(result) {
	    	
	    		// 5. 메세지 창을 띄우고 페이지 전환필요
		    	// 경고 메세지 객체 생성
		    	Alert alert = new Alert(AlertType.INFORMATION);
		    	alert.setContentText("Music Producing 회원가입 성공 [포인트 1000 지급]"); // 메세지 창에 뜨는 말
		    	alert.setHeaderText("회원가입성공"); 
		    	alert.setTitle("알림");
		    	alert.showAndWait(); // 메세지를 띄우고 입력시까지 기다림
		    	LoginController.getInstance().loadpage("test2");
		    	
	    	} else {
	    		System.out.println("DB 오류");
	    	}
	    	
	    	
	    
	    }
	    
	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    	signupconfirm.setText("");
	    }
}
