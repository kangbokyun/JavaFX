package Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.MemberDAO;
import domain.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

public class MyInfoController implements Initializable {
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// �α��ε� ���̵��� ȸ�������� DB���� ã��
		String loginId = MainPageController.getInstance().getLogId();
		Member member = MemberDAO.getMemberDAO().getmember(loginId);
		
		MIIdLabel.setText(member.getM_id());
		MINameLabel.setText(member.getM_name());
		MIEmailLabel.setText(member.getM_email());
		MIPointLabel.setText(member.getM_point() + ""); // int�� String���� ���
	}
	
    @FXML
    private Label MIPointLabel;
	
	@FXML
    private Button MIDeletebtn;

    @FXML
    private Label MIEmailLabel;

    @FXML
    private Label MIIdLabel;

    @FXML
    private Label MINameLabel;

    @FXML
    private Button MIUpadatebtn;

    @FXML
    void MIDeleteAction(ActionEvent event) {
    	// 1. �޼���â ����
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("�˸�");
    	alert.setContentText("ȸ��Ż��");
    	alert.setHeaderText("����?");
    	
    	Optional<ButtonType> optional = alert.showAndWait();
    	if(optional.get() == ButtonType.OK) {
    		// ȸ��Ż�� ����
    		boolean result = MemberDAO.getMemberDAO().delete(MIIdLabel.getText());
    		
    		Alert alert2 = new Alert(AlertType.CONFIRMATION);
    		
    		if(result) {
    			
    			alert2.setHeaderText("����");
    			
    			// ������ �α׾ƿ�
    			MainPageController.getInstance().LogoutAction(event);
    			
    		} else {
    			
    			alert2.setHeaderText("DB����");
    			alert2.setTitle("�˸�");
    			alert2.showAndWait();
    			
    		}
    	}
    }

    @FXML
    void MIUpdateAction(ActionEvent event) {
    	MainPageController.getInstance().LoadPage("MyInfoUpdatePage");
    }

}
