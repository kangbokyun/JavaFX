package Controller;

import dao.MemberDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class FindIdController  {

    @FXML
    private AnchorPane SignupPane;

    @FXML
    private TextField findidEmailtxt;

    @FXML
    private TextField findidNametxt;

    @FXML
    private Button findidbtn;

    @FXML
    private Label findidbtnBack;

    @FXML
    private Label findidconfirm;

    @FXML
    void findid(ActionEvent event) {
    	// DAO ��ü �� findid �޼ҵ� ȣ��
    	String result = MemberDAO.getMemberDAO().findid(findidNametxt.getText(), findidEmailtxt.getText());
    	
    	if(result != null) {
    		findidconfirm.setText("���̵� : " + result);
    	}
    }

    @FXML
    void findidBack(MouseEvent event) {
    	LoginController.getInstance().loadpage("test2");
    }

}
