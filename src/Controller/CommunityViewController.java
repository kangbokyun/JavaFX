package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import domain.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class CommunityViewController implements Initializable {
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Board board = CommunityListController.board;
		CommunityViewTitleLabel.setText(board.getB_title());
		CommunityViewWriteLabel.setText(board.getB_write());
		CommunityViewContentsLabel.setText(board.getB_cntents());
		CommunityViewCountLabel.setText(board.getB_view() + "");
		
		
//		if(!MainPageController.getInstance().getLogId().equals(board.getB_write())) {
//			CommunityViewUpdatebtn.setVisible(false);
//			CommunityViewDeletebtn.setVisible(false);
//		}
	}
	
	
    @FXML
    private Label CommunityViewBack;

    @FXML
    private Label CommunityViewContentsLabel;

    @FXML
    private Label CommunityViewCountLabel;

    @FXML
    private Label CommunityViewDateLabel;

    @FXML
    private Button CommunityViewDeletebtn;

    @FXML
    private TableView<?> CommunityViewReplyList;

    @FXML
    private Button CommunityViewReplybtn;

    @FXML
    private Label CommunityViewTitleLabel;

    @FXML
    private Button CommunityViewUpdatebtn;

    @FXML
    private Label CommunityViewWriteLabel;

    @FXML
    void CommunityViewBackOMC(MouseEvent event) {
    	MainPageController.getInstance().LoadPage("CommunityListPage");
    }

    @FXML
    void CommunityViewDeleteAction(ActionEvent event) {

    }

    @FXML
    void CommunityViewReplyAction(ActionEvent event) {

    }

    @FXML
    void CommunityViewUpdateAction(ActionEvent event) {

    }
}
