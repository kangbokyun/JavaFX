package Controller;

import dao.BoardDAO;
import dao.MemberDAO;
import domain.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CommunityController {
	 	@FXML
	    private Button CommunityBackbtn;

	    @FXML
	    private TextArea CommunityContentstxt;

	    @FXML
	    private Button CommunityRegistbtn;

	    @FXML
	    private TextField CommunityTitletxt;

	    @FXML
	    void CommunityBackAction(ActionEvent event) {
	    	MainPageController.getInstance().LoadPage("CommunityListPage");
	    }

	    @FXML
	    void CommunityRegistAction(ActionEvent event) {
	    	Board board = new Board(CommunityTitletxt.getText(), CommunityContentstxt.getText(), MainPageController.getInstance().getLogId());
	    	boolean result = BoardDAO.getboardDAO().write(board);
	    	
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	if(result) {
	    		alert.setTitle("등록완");
	    		alert.setHeaderText("등록됨");
	    		alert.showAndWait();
	    		
//	    		CommunityContentstxt.setText("");
//	    		CommunityTitletxt.setText("");
	    		
	    		MainPageController.getInstance().LoadPage("CommunityListPage");
	    	} else {
	    		alert.setTitle("실패");
	    		alert.setHeaderText("실패했음");
	    		alert.showAndWait();
	    	}
	    }

}
