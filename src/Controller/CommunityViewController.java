package Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.BoardDAO;
import domain.Board;
import domain.Reply;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class CommunityViewController implements Initializable {
	Board board = CommunityListController.board;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		replyTableLoad();
		BoardDAO.getboardDAO().ViewUpdate(board.getB_no()); // 조회수증가
		CommunityViewTitleLabel.setText(board.getB_title());
		CommunityViewWriteLabel.setText(board.getB_write());
		CommunityViewContentsLabel.setText(board.getB_cntents());
		CommunityViewCountLabel.setText(board.getB_view() + "");
		CommunityViewDateLabel.setText(board.getB_date());
		
		
		if(!MainPageController.getInstance().getLogId().equals(board.getB_write())) {
			CommunityViewUpdatebtn.setVisible(false);
			CommunityViewDeletebtn.setVisible(false);
		}
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
    private TableView<Reply> CommunityViewReplyList;

    @FXML
    private Button CommunityViewReplybtn;

    @FXML
    private Label CommunityViewTitleLabel;

    @FXML
    private Button CommunityViewUpdatebtn;

    @FXML
    private Label CommunityViewWriteLabel;
    
    @FXML
    private TextArea ReplyContentstxt;

    @FXML
    void CommunityViewBackOMC(MouseEvent event) {
    	MainPageController.getInstance().LoadPage("CommunityListPage");
    }

    @FXML
    void CommunityViewDeleteAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("ㄹㅇ?");
    	alert.setHeaderText("ㄱ?");
    	Optional<ButtonType> optional = alert.showAndWait();
    	if(optional.get() == ButtonType.OK) {
    		if(CommunityViewWriteLabel.getText().equals(MainPageController.getInstance().getLogId())) {
    			boolean result = BoardDAO.getboardDAO().delete(board.getB_no());
    			MainPageController.getInstance().LoadPage("CommunityListPage");
    		} else {
    			System.out.println("완");
    		}
    		
    	} else {
    		System.out.println("DB");
    	}
    	
    }

    @FXML
    void CommunityViewReplyAction(ActionEvent event) {
    	Reply reply = new Reply(ReplyContentstxt.getText(), MainPageController.getInstance().getLogId(), board.getB_no());
    	boolean result = BoardDAO.getboardDAO().replyWrite(reply);
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	if(result) {
    		alert.setHeaderText("했음");
    		alert.showAndWait();
    		replyTableLoad();
    		ReplyContentstxt.setText("");
    	} else {
    		alert.setHeaderText("못했음");
    		alert.showAndWait();
    	}
    }

    @FXML
    void CommunityViewUpdateAction(ActionEvent event) {
    	
    }
    
    // 테이블 값 로드 메소드
    public void replyTableLoad() {
    	ObservableList<Reply> obList = BoardDAO.getboardDAO().replyList(board.getB_no());
    	
    	TableColumn tc = CommunityViewReplyList.getColumns().get(0);
//    	tc.setCellValueFactory(new PropertyValueFactory<>("r_no"));
//    	tc = CommunityViewReplyList.getColumns().get(1);
    	tc.setCellValueFactory(new PropertyValueFactory<>("r_contents"));
//    	tc = CommunityViewReplyList.getColumns().get(2);
//    	tc.setCellValueFactory(new PropertyValueFactory<>("r_write"));
//    	tc = CommunityViewReplyList.getColumns().get(3);
//    	tc.setCellValueFactory(new PropertyValueFactory<>("r_date"));
//    	tc = CommunityViewReplyList.getColumns().get(4);
//    	tc.setCellValueFactory(new PropertyValueFactory<>("r_b_no"));
    	
    	CommunityViewReplyList.setItems(obList);
    }
}
