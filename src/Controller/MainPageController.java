package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// Initializable : 초기화 인터페이스
public class MainPageController implements Initializable {
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		LoginIDLabel.setText(LoginController.getInstance().getlogId());
	}
	
	  @FXML
	    private AnchorPane CenterAnchorPane;

	    @FXML
	    private Button Chattingbtn;

	    @FXML
	    private Button Communitybtn;

	    @FXML
	    private Button Homebtn;

	    @FXML
	    private AnchorPane LeftAnchorPane;

	    @FXML
	    private Label LoginIDLabel;

	    @FXML
	    private Button Logoutbtn;

	    @FXML
	    private BorderPane MainBoarderPane;

	    @FXML
	    private Button MyInfobtn;

	    @FXML
	    private Button Productbtn;

	    @FXML
	    void ChattingAction(ActionEvent event) {
	    	LoadPage("MyInfoPage");
	    }

	    @FXML
	    void CommunityAction(ActionEvent event) {
	    	LoadPage("CommunityListPage");
	    }

	    @FXML
	    void HomeAction(ActionEvent event) {
	    	LoadPage("totalPage");
	    }

	    @FXML
	    void LogoutAction(ActionEvent event) {
	    	// 메세지 창 띄우기
	    	Alert alert = new Alert(AlertType.CONFIRMATION); // 확인취소 버튼을 만들어줌
	    	alert.setContentText("로그아웃");
	    	alert.setHeaderText("로그아웃 ㄱ?");
	    	alert.setTitle("확인");
	    	
	    	// 메세지 버튼을 눌렀을 때 (Optional 클래스 : null값을 받아주는 클래스)
	    	Optional<ButtonType> optional = alert.showAndWait(); // 버튼을 눌렀을 때 어떤 값인지 받아줌
	    	if(optional.get() == ButtonType.OK) { // 버튼이 ok가 눌렸을 때
	    		Logoutbtn.getScene().getWindow().hide(); // mainpage는 숨기고 
	    		
	    		try { // loginpage를 띄움

	    			Stage stage = new Stage();
		    		Parent parent;
					parent = FXMLLoader.load(getClass().getResource("/Day1/test2.fxml"));
					Scene scene = new Scene(parent);
					stage.setScene(scene);
					stage.show();

	    		} catch (IOException e) {
					System.out.println(e.getMessage());
				}
	    	}
	    }

	    @FXML
	    void MyInfoAction(ActionEvent event) {
	    	LoadPage("MyInfoPage");
	    }

	    @FXML
	    void ProductAction(ActionEvent event) {
	    	LoadPage("ProductPage");
	    }
	    
	    // 현재(보더가 있는 페이지) 클래스 객체화---------------------------------------------------------------------------------
	    // 선언
	    public static MainPageController instance;
	    
	    // 생성자
	    public MainPageController() {
	    	// 현재 클래스의 모든 멤버를 포함
	    	instance = this;
	    }
	    
	    // 객체 반환
	    public static MainPageController getInstance() {
	    	return instance;
	    }
	    
	    //----------------------------------------------------------------------------------------------------------------------
	    
	    // 로그인 된 아이디를 빼오는 메소드 생성
	    public String getLogId() {
	    	return LoginIDLabel.getText();
	    }
	    // 이렇게 해도 됨 ↑
	    // public static String getLoginID = MainPageController.getInstance().LoginIDLabel.getText();
	    
	    //----------------------------------------------------------------------------------------------------------------------

	    public void LoadPage(String page) {
	    	try {
	    		
				Parent parent = FXMLLoader.load(getClass().getResource("/Day1/" + page + ".fxml"));
				MainBoarderPane.setCenter(parent);
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
	    }
}
