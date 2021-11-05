package Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.BoardDAO;
import dao.MemberDAO;
import dao.ProductDAO;
import domain.Board;
import domain.Member;
import domain.Product;
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
import javafx.scene.control.cell.PropertyValueFactory;

public class MyInfoController implements Initializable {
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// �α��ε� ���̵��� ȸ�������� DB���� ã��
		String loginId = MainPageController.getInstance().getLogId();
		Member member = MemberDAO.getMemberDAO().getmember(loginId);

		ObservableList<Board> boards = BoardDAO.getboardDAO().myBoardList(loginId);
		MyInfoCommTable.setItems(boards);
		
		TableColumn tc = MyInfoCommTable.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_no"));
		tc = MyInfoCommTable.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_title"));
		tc = MyInfoCommTable.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_date"));
		tc = MyInfoCommTable.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("b_view"));
		
		// ���� ����� ��ǰ
		int m_no = ProductDAO.getProductDAO().mnoCheck(loginId);
		ObservableList<Product> products = ProductDAO.getProductDAO().myProductList(m_no);
		MyInfoProdTable.setItems(products);
//		
		tc = MyInfoProdTable.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("p_name"));
		tc = MyInfoProdTable.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("p_category"));
		tc = MyInfoProdTable.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("p_price"));
		tc = MyInfoProdTable.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("p_activation"));
		tc = MyInfoProdTable.getColumns().get(4);
		tc.setCellValueFactory(new PropertyValueFactory<>("p_date"));
		
		
		MIIdLabel.setText(member.getM_id());
		MINameLabel.setText(member.getM_name());
		MIEmailLabel.setText(member.getM_email());
		MIPointLabel.setText(member.getM_point() + ""); // int�� String���� ���
		
	}
	
    @FXML
    private TableView<Board> MyInfoCommTable;

    @FXML
    private TableView<Product> MyInfoProdTable;
	
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
