package Controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import dao.ProductDAO;
import domain.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ProductRegisterUpdateController implements Initializable {
	
	Product product = ProductController.product;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ProductRegisterName.setText(product.getP_name());
		ProductRegisterPrice.setText(product.getP_price() + "");
		ProductRegisterContents.setText(product.getP_contents());
		
		
		Image image = new Image(product.getP_img());
		ProductRegisterIMG.setImage(image);
		ProductRegisterPath.setText(product.getP_img());
		
		if(product.getP_category().equals(ProductRegisterRB1.getText())) {
			ProductRegisterRB1.setSelected(true);
		}
		if(product.getP_category().equals(ProductRegisterRB2.getText())) {
			ProductRegisterRB2.setSelected(true);
		}
		if(product.getP_category().equals(ProductRegisterRB3.getText())) {
			ProductRegisterRB3.setSelected(true);
		}
		if(product.getP_category().equals(ProductRegisterRB4.getText())) {
			ProductRegisterRB4.setSelected(true);
		}
		
		pimg = product.getP_img();
	}
	
    @FXML
    private ToggleGroup Category;

    @FXML
    private AnchorPane CenterAnchorPane;

    @FXML
    private Button ProductRegisterCanclebtn;

    @FXML
    private TextArea ProductRegisterContents;

    @FXML
    private Button ProductRegisterFindbtn;

    @FXML
    private ImageView ProductRegisterIMG;

    @FXML
    private TextField ProductRegisterName;

    @FXML
    private Label ProductRegisterPath;

    @FXML
    private TextField ProductRegisterPrice;

    @FXML
    private RadioButton ProductRegisterRB1;

    @FXML
    private RadioButton ProductRegisterRB2;

    @FXML
    private RadioButton ProductRegisterRB3;

    @FXML
    private RadioButton ProductRegisterRB4;

    @FXML
    private Button ProductRegisterUpdatebtn;

    @FXML
    void ProductRegisterCancleAction(ActionEvent event) {
    	MainPageController.getInstance().LoadPage("ProductPage");
    }

    @FXML
    void ProductRegisterFindAction(ActionEvent event) {
    	FileChooser chooser = new FileChooser();
    	chooser.getExtensionFilters().add(new ExtensionFilter("그림파일", "jpg", "png", "gif"));
    	
    	// 선택한 파일의 경로보기
    	File file = chooser.showOpenDialog(stage);
    	ProductRegisterPath.setText(file.getPath());
    	
    	pimg = file.toURI().toString();
    	Image image = new Image(pimg);
    	ProductRegisterIMG.setImage(image);
    }

    @FXML
    void ProductRegisterUpdateAction(ActionEvent event) {
    	String p_name = ProductRegisterName.getText();
    	String p_contents = ProductRegisterContents.getText();
    	int p_price = Integer.parseInt(ProductRegisterPrice.getText());
    	
    	String category = "";
    	if(ProductRegisterRB1.isSelected()) {
    		category = "의류";
    	}
    	if(ProductRegisterRB2.isSelected()) {
    		category = "신발";
    	}
    	if(ProductRegisterRB3.isSelected()) {
    		category = "가방";
    	}
    	if(ProductRegisterRB4.isSelected()) {
    		category = "ACC";
    	}
    	
    	
    	Product product2 = new Product(product.getP_no(), p_name, pimg, p_contents, category, p_price, 0, "0", 0);
    	
    	boolean result = ProductDAO.getProductDAO().update(product2);
    	if(result) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.showAndWait();
    	}
    }
    
    private Stage stage; // 파일선택 스테이지
    private String pimg; // 선택된 파일의 경로를 저장

}
