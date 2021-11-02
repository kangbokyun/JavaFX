package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.MemberDAO;
import domain.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class MyInfoUpdateController implements Initializable{
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String id = MainPageController.getInstance().getLogId();
		Member member = MemberDAO.getMemberDAO().getmember(id);
		
		MIIdLabel.setText(member.getM_id());
		MIPointLabel.setText(member.getM_point() + "");
		
	}
	
	
	@FXML
    private Button MIDeletebtn;

    @FXML
    private Label MIIdLabel;

    @FXML
    private Label MIPointLabel;

    @FXML
    private Button MIUpadatebtn;

    @FXML
    private TextField MyInfoUpdateEmailbtn;

    @FXML
    private TextField MyInfoUpdateNametxt;

    @FXML
    void MIDeleteAction(ActionEvent event) {
    	MainPageController.getInstance().LoadPage("MyInfoPage");
    }

    @FXML
    void MIUpdateAction(ActionEvent event) {
    	boolean result = MemberDAO.getMemberDAO().Update(MIIdLabel.getText(), MyInfoUpdateNametxt.getText(), MyInfoUpdateEmailbtn.getText());
    	if(result) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("수정");
    		alert.setHeaderText("했음");
    		alert.showAndWait();
    		
    		MainPageController.getInstance().LoadPage("MyInfoPage");
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("안되는데?");
    		alert.setHeaderText("DB오류인듯?");
    		alert.showAndWait();
    	}
    }

    @FXML
    void MyInfoUpdateEmailAction(ActionEvent event) {

    }

    @FXML
    void MyInfoUpdateNameAction(ActionEvent event) {

    }
}
