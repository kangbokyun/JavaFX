package Controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SignupController {
   
	

    @FXML
    private AnchorPane SignupPane;

    @FXML
    private Label btnBack;

    @FXML
    void Back(MouseEvent event) {
    	// �޼ҵ带 ȣ���ϴ� ��� 
    	// 1. static
    	// 2. ��ü�� ���� ȣ��
    	LoginController.getInstance().loadpage("test2");
    	
    	// ������ �޸� ����� ���� this Ű���带 ����� getInstance ȣ��
//    	LoginController controller = new LoginController();
//    	controller.getInstance().loadpage("test2");
    }

}
