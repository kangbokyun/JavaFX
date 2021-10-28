package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FindIdController  {

    @FXML
    private javafx.scene.layout.AnchorPane AnchorPane;

    @FXML
    private Button btnlogin;

    @FXML
    private Label findidback;

    @FXML
    private javafx.scene.layout.AnchorPane loginPane;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtemail;

    @FXML
    void back(MouseEvent event) {
    	LoginController.getInstance().loadpage("test2");
    }

    @FXML
    void findidresult(ActionEvent event) {
    	if(txtid.getText().equals("admin")) {
    		System.out.println("dd");
    	} else {
    		System.out.println("ss");
    	}
    }

}
