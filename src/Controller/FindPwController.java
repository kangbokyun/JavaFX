package Controller;

import dao.MemberDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class FindPwController {

    @FXML
    private AnchorPane FindPwPane;

    @FXML
    private Button btnfindpw;

    @FXML
    private TextField findPwIdtxt;

    @FXML
    private TextField findpwEmailtxt;

    @FXML
    private Label findpwbtnBack;

    @FXML
    private Label findpwconfirm;

    @FXML
    void findpwBack(MouseEvent event) {
    	LoginController.getInstance().loadpage("test2");
    }

    @FXML
    void findpwbtn(ActionEvent event) {
    	String result = MemberDAO.getMemberDAO().findpw(findPwIdtxt.getText(), findpwEmailtxt.getText());
    	if(result != null) {
    		findpwconfirm.setText("비밀번호 : " + result);
    	}
    }
}
