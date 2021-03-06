package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.MemberDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController implements Initializable{ // 초기화
	// 컨트롤명 선언
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		labelconfirm.setText("");
	}
	
	// 기존 클래스를 객체화
	private static LoginController instance;
	
	public LoginController() {
		// 그냥 this만 쓰면 현재 클래스 자체의 메모리를 집어 넣는거임
		instance = this;
	}
	
	public static LoginController getInstance() {
		return instance;
	}
	
	public String getlogId() {
		return txtid.getText();
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
    	loadpage("test2_Findid");
    }

    @FXML
    void findpw(MouseEvent event) {
    	loadpage("test2_Findpw");
    }

    @FXML
    void onaction(ActionEvent event) {
      	// 1. DAO 객체 메소드 호출
    	boolean result = MemberDAO.getMemberDAO().login(txtid.getText(), txtpw.getText());
    	
    	if(result) {
    		labelconfirm.setText("로그인성공");
    		
    		// 로그인 성공시 10점 업데이트
    		MemberDAO.getMemberDAO().pointUpdate(txtid.getText(), 10);
    		
    		try {

    			// 기존스테이지 끄기 -> 해당윈도우를 가져와 숨기기
    			btnlogin.getScene().getWindow().hide();
    			
    			// 메인페이지
    			Stage stage = new Stage();
    			Parent parent;
				parent = FXMLLoader.load(getClass().getResource("/Day1/MainPage.fxml"));
				Scene scene = new Scene(parent);
				stage.setScene(scene);
				
				// 스테이지 아이콘
				// 1. 이미지 불러오기
				Image image = new Image("file:C:\\Users\\ez201206\\Desktop\\KakaoTalk_20211028_150125977.png");
				stage.getIcons().add(image);
				
				stage.show();
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
    		
    	} else {
    		labelconfirm.setText("로그인실패");
    	}
    }
  


    @FXML
    void Signup(MouseEvent event) { 
    	loadpage("test2_signup"); 
    }

	
	// board 센터 변경
	public void loadpage(String page)  {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/Day1/" + page + ".fxml"));
			BoardPane.setCenter(parent);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
