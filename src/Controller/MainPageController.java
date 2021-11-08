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

// Initializable : �ʱ�ȭ �������̽�
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
	    	// �޼��� â ����
	    	Alert alert = new Alert(AlertType.CONFIRMATION); // Ȯ����� ��ư�� �������
	    	alert.setContentText("�α׾ƿ�");
	    	alert.setHeaderText("�α׾ƿ� ��?");
	    	alert.setTitle("Ȯ��");
	    	
	    	// �޼��� ��ư�� ������ �� (Optional Ŭ���� : null���� �޾��ִ� Ŭ����)
	    	Optional<ButtonType> optional = alert.showAndWait(); // ��ư�� ������ �� � ������ �޾���
	    	if(optional.get() == ButtonType.OK) { // ��ư�� ok�� ������ ��
	    		Logoutbtn.getScene().getWindow().hide(); // mainpage�� ����� 
	    		
	    		try { // loginpage�� ���

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
	    
	    // ����(������ �ִ� ������) Ŭ���� ��üȭ---------------------------------------------------------------------------------
	    // ����
	    public static MainPageController instance;
	    
	    // ������
	    public MainPageController() {
	    	// ���� Ŭ������ ��� ����� ����
	    	instance = this;
	    }
	    
	    // ��ü ��ȯ
	    public static MainPageController getInstance() {
	    	return instance;
	    }
	    
	    //----------------------------------------------------------------------------------------------------------------------
	    
	    // �α��� �� ���̵� ������ �޼ҵ� ����
	    public String getLogId() {
	    	return LoginIDLabel.getText();
	    }
	    // �̷��� �ص� �� ��
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
