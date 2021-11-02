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
		// DAO ȣ��
		ObservableList<Board> obserList = BoardDAO.getboardDAO().boardList();
		
		// TableView�� �ʵ� ��������
		// 0��° �÷���(b_No)�� tc�� �ִ´�.
		TableColumn tc = CommunityBoardList.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_no"));
		
		// TableView�� ����Ʈ 
		tc = CommunityBoardList.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_title"));
		tc = CommunityBoardList.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_write"));
		tc = CommunityBoardList.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_date"));
		tc = CommunityBoardList.getColumns().get(4);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_view"));
		
		// ���̺� ��ü�ֱ�
		CommunityBoardList.setItems(obserList);

		// 1ȸ�� �޼ҵ�
		// ���ٽ�
//		CommunityBoardList.setOnMouseClicked( e -> { ���� return } );
		// ���������� onMouseClicked�� ���� �����Ͱ� ���� ����
		
		// ���̺��� Ŭ���� �÷��� item���� ���� �� �ֵ�
		// Ŭ���� �������� ���� ������ ��ȯ
		CommunityBoardList.setOnMouseClicked( e -> {  
															// �μ� -> ���� : �͸�޼ҵ�
			if(e.getButton().equals(MouseButton.PRIMARY)) { // �ش� �̺�Ʈ�� Ŭ���̸�
				// ���̺�信 ���õ� ���� ������(��ü)
				 board = CommunityBoardList.getSelectionModel().getSelectedItem();

				// ��ȸ�� ����
				
				
				MainPageController.getInstance().LoadPage("CommunityView");
			}
			
		});
	}
	
	
	   @FXML
	    private TableView<Board> CommunityBoardList;
	   // Table�� ���� ��ü�� Ŭ�������� ���׸��� �־��

	   public static Board board;

	    @FXML
	    private Button ConmmunityListWritetxt;

	    @FXML
	    void ConmmunityListWriteAction(ActionEvent event) {
	    	MainPageController.getInstance().LoadPage("CommunityPage");
	    }

}
