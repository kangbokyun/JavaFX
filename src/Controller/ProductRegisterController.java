package Controller;

import java.io.File;

import dao.ProductDAO;
import domain.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class ProductRegisterController {
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
    private Button ProductRegisterbtn;

    @FXML
    void ProductRegisterAction(ActionEvent event) {
    	String pname = ProductRegisterName.getText();
    	String pcontents = ProductRegisterContents.getText();
    	int pprice = Integer.parseInt(ProductRegisterPrice.getText());
    	String pcategory = "";
    	if(ProductRegisterRB1.isSelected()) { // �ش� ������ư�� Ŭ������ ��
    		pcategory = "�Ƿ�";
    	}
    	if(ProductRegisterRB2.isSelected()) {
    		pcategory = "�Ź�";
    	}
    	if(ProductRegisterRB3.isSelected()) {
    		pcategory = "����";
    	}
    	if(ProductRegisterRB4.isSelected()) {
    		pcategory = "ACC";
    	}
    	
    	// �α��ε� ���̵��� ȸ����ȣ �˻� db�� ���� activation�� �־�� ��
    	// ����
    	int m_no = ProductDAO.getProductDAO().mnoCheck(MainPageController.getInstance().getLogId());
    	
    	// ��üȭ
    	Product product = new Product(pname, pimg, pcontents, pcategory, pprice, 1, m_no);
    	
    	boolean result = ProductDAO.getProductDAO().register(product);
    	if(result) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.showAndWait();
    		MainPageController.getInstance().LoadPage("ProductPage");
    	}
    }
    
    // ���ϰ�� ã��
    private String pimg;
    // ���ϰ�� ã�� ȭ��
    private Stage stage; 

    @FXML
    void ProductRegisterCancleAction(ActionEvent event) {
    	MainPageController.getInstance().LoadPage("ProductPage");
    }

    @FXML
    void ProductRegisterFindAction(ActionEvent event) {
    	// ���ϼ��� Ŭ����
    	FileChooser chooser = new FileChooser(); // FileChooser() : ���� ���ý� �ش� ������ ��ΰ��� Ŭ������ ����
    	
    	// ���� �������� ����
    	chooser.getExtensionFilters().add(new ExtensionFilter("�׸����� : ImageFile", "*png", "*gif", "*jpg"));
    	// getExtensionFilters() : Ȯ���ڸ��� �߰��ϰų� ���ų� �ƹ��ų� �� �� ���� / ������ ������ ���Ϳ����� �� �� ����
    	// ����� getExtensionFilters(new ExtensionFilters(""))�� �Ѵ�
    	
    	// �������� ����
    	// ������ ������ ����Ŭ������ ���� File.toString() syso�ϸ� �� �� ����
    	File file =  chooser.showOpenDialog(stage);
    	
    	ProductRegisterPath.setText(file.toString()); // ������ ���� ���
    	
    	pimg = file.toURI().toString();
    	System.out.println(pimg); // ������ ���� ��θ� ��ǻ�Ͱ� �� �� �ְ� ����Ʈ�� �ٲ� uri
    	
    	Image image = new Image(pimg);
    	ProductRegisterIMG.setImage(image);
    }
}
