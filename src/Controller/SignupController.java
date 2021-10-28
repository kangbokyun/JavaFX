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
    	// 메소드를 호출하는 방법 
    	// 1. static
    	// 2. 객체를 통한 호출
    	LoginController.getInstance().loadpage("test2");
    	
    	// 동일한 메모리 사용을 위해 this 키워드를 사용한 getInstance 호출
//    	LoginController controller = new LoginController();
//    	controller.getInstance().loadpage("test2");
    }

}
