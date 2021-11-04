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
		// DB���� ��ǰ ��� ��������
		ObservableList<Product> products = ProductDAO.getProductDAO().productList();
		ProductDeletebtn.setVisible(false);
		ProductUpdatebtn.setVisible(false);
		
		// ��ǰ����� ���̺�信 �־��ֱ�
		ProductListTable.setItems(products);
		
		// ���̺�信 ���� �ϳ��� �����ͼ� ����Ʈ �� ��ü�� �ʵ�� ����
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
		
		// ���̺�信�� Ŭ������ �� ������ ��������
		// 1. ���̺�信 Ŭ�� �̺�Ʈ
		ProductListTable.setOnMouseClicked(e -> {
			// Ŭ�� �̺�Ʈ�� ���콺 Ŭ���� ������
			if(e.getButton().equals(MouseButton.PRIMARY)) {
				// ���̺� �信�� Ŭ���� ���� ������(��ü)
				product = ProductListTable.getSelectionModel().getSelectedItem();
				
				// ���õ� ��ü�� �̹��� ��� ��������
				Image image = new Image(product.getP_img());
				ProductIMG.setImage(image);

				// �� ��
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
    	alert.setHeaderText("����?");
    	Optional<ButtonType> optional = alert.showAndWait();
    	if(optional.get() == ButtonType.OK) {
    		boolean result = ProductDAO.getProductDAO().delete(product.getP_no());
    		Alert alert2 = new Alert(AlertType.INFORMATION);
    		if(result) {
    			alert2.setTitle("����");
    			MainPageController.getInstance().LoadPage("ProductPage");
    		} else {
    			alert2.setTitle("����");
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
