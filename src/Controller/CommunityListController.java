package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.BoardDAO;
import domain.Board;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CommunityListController implements Initializable{
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// DAO 호출
		ObservableList<Board> obserList = BoardDAO.getboardDAO().boardList();
		
		// TableView의 필드 가져오기
		// 0번째 컬럼값(b_No)을 tc에 넣는다.
		TableColumn tc = CommunityBoardList.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_no"));
		
		// TableView에 리스트 
		tc = CommunityBoardList.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_title"));
		tc = CommunityBoardList.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_write"));
		tc = CommunityBoardList.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_date"));
		tc = CommunityBoardList.getColumns().get(4);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_view"));
		
		// 테이블에 객체넣기
		CommunityBoardList.setItems(obserList);

		// 1회성 메소드
		// 람다식
//		CommunityBoardList.setOnMouseClicked( e -> { 정의 return } );
		// 씬빌더에서 onMouseClicked에 값을 넣은것과 같은 역할
		
		// 테이블뷰는 클릭한 컬럼의 item값을 빼올 수 있따
		// 클릭한 아이템을 갖고 페이지 전환
		CommunityBoardList.setOnMouseClicked( e -> {  
															// 인수 -> 정의 : 익명메소드
			if(e.getButton().equals(MouseButton.PRIMARY)) { // 해당 이벤트가 클릭이면
				// 테이블뷰에 선택된 모델의 아이템(객체)
				 board = CommunityBoardList.getSelectionModel().getSelectedItem();

				// 조회수 증가
				
				
				MainPageController.getInstance().LoadPage("CommunityView");
			}
			
		});
	}
	
	
	   @FXML
	    private TableView<Board> CommunityBoardList;
	   // Table에 넣을 객체의 클래스명을 제네릭에 넣어라

	   public static Board board;

	    @FXML
	    private Button ConmmunityListWritetxt;

	    @FXML
	    void ConmmunityListWriteAction(ActionEvent event) {
	    	MainPageController.getInstance().LoadPage("CommunityPage");
	    }

}
