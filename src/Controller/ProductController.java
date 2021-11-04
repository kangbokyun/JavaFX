package Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.ProductDAO;
import domain.Product;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

public class ProductController implements Initializable {
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// DB에서 제품 목록 가져오기
		ObservableList<Product> products = ProductDAO.getProductDAO().productList();
		ProductDeletebtn.setVisible(false);
		ProductUpdatebtn.setVisible(false);
		
		// 제품목록을 테이블뷰에 넣어주기
		ProductListTable.setItems(products);
		
		// 테이블뷰에 열을 하나씩 가져와서 리스트 내 객체에 필드와 연결
		TableColumn tc = ProductListTable.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("p_name"));
		tc = ProductListTable.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("p_category"));
		tc = ProductListTable.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("p_price"));
		tc = ProductListTable.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("p_activation"));
		tc = ProductListTable.getColumns().get(4);
		tc.setCellValueFactory(new PropertyValueFactory<>("p_date"));
		
		// 테이블뷰에서 클릭했을 때 아이템 가져오기
		// 1. 테이블뷰에 클릭 이벤트
		ProductListTable.setOnMouseClicked(e -> {
			// 클릭 이벤트가 마우스 클릭과 같으면
			if(e.getButton().equals(MouseButton.PRIMARY)) {
				// 테이블 뷰에서 클릭한 모델의 아이템(객체)
				product = ProductListTable.getSelectionModel().getSelectedItem();
				
				// 선택된 객체내 이미지 경로 가져오기
				Image image = new Image(product.getP_img());
				ProductIMG.setImage(image);

				// 그 외
				ProductNametxt.setText(product.getP_name());
				ProductContentstxt.setText(product.getP_contents());
				ProductPricetxt.setText(String.format( "%,d", product.getP_price()));
				ProductIdtxt.setText(ProductDAO.getProductDAO().midCheck(product.getM_no()));
			}
			
			if(MainPageController.getInstance().getLogId().equals(ProductIdtxt.getText())) {
				ProductDeletebtn.setVisible(true);
				ProductUpdatebtn.setVisible(true);
			} else {
				ProductDeletebtn.setVisible(false);
				ProductUpdatebtn.setVisible(false);
			}
		});
	}
	
	public static Product product;
	
    @FXML
    private AnchorPane CenterAnchorPane;

    @FXML
    private Label ProductContentstxt;

    @FXML
    private Button ProductDeletebtn;

    @FXML
    private ImageView ProductIMG;

    @FXML
    private Label ProductIdtxt;

    @FXML
    private TableView<Product> ProductListTable;

    @FXML
    private Label ProductNametxt;

    @FXML
    private Label ProductPricetxt;

    @FXML
    private Button ProductRegisterbtn1;

    @FXML
    private Button ProductUpdatebtn;

    @FXML
    void ProductRegisterAction(ActionEvent event) {
    	MainPageController.getInstance().LoadPage("ProductRegisterPage");
    }

    @FXML
    void ProductDeleteAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("ㄹㅇ?");
    	Optional<ButtonType> optional = alert.showAndWait();
    	if(optional.get() == ButtonType.OK) {
    		boolean result = ProductDAO.getProductDAO().delete(product.getP_no());
    		Alert alert2 = new Alert(AlertType.INFORMATION);
    		if(result) {
    			alert2.setTitle("ㅂㅂ");
    			MainPageController.getInstance().LoadPage("ProductPage");
    		} else {
    			alert2.setTitle("ㄴㄴ");
    		}
    	}
    }

    @FXML
    void ProductUpdateAction(ActionEvent event) {
    	MainPageController.getInstance().LoadPage("productRegisterUpdatePage");
    }

    public ProductController() {

    }
    
    
}
