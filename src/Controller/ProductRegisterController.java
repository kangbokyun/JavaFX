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
    	if(ProductRegisterRB1.isSelected()) { // 해당 라디오버튼을 클릭했을 때
    		pcategory = "의류";
    	}
    	if(ProductRegisterRB2.isSelected()) {
    		pcategory = "신발";
    	}
    	if(ProductRegisterRB3.isSelected()) {
    		pcategory = "가방";
    	}
    	if(ProductRegisterRB4.isSelected()) {
    		pcategory = "ACC";
    	}
    	
    	// 로그인된 아이디의 회원번호 검색 db를 만들어서 activation에 넣어야 함
    	// 임의
    	int m_no = ProductDAO.getProductDAO().mnoCheck(MainPageController.getInstance().getLogId());
    	
    	// 객체화
    	Product product = new Product(pname, pimg, pcontents, pcategory, pprice, 1, m_no);
    	
    	boolean result = ProductDAO.getProductDAO().register(product);
    	if(result) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.showAndWait();
    		MainPageController.getInstance().LoadPage("ProductPage");
    	}
    }
    
    // 파일경로 찾기
    private String pimg;
    // 파일경로 찾을 화면
    private Stage stage; 

    @FXML
    void ProductRegisterCancleAction(ActionEvent event) {
    	MainPageController.getInstance().LoadPage("ProductPage");
    }

    @FXML
    void ProductRegisterFindAction(ActionEvent event) {
    	// 파일선택 클래스
    	FileChooser chooser = new FileChooser(); // FileChooser() : 파일 선택시 해당 파일의 경로값을 클래스에 저장
    	
    	// 파일 스테이지 설정
    	chooser.getExtensionFilters().add(new ExtensionFilter("그림파일 : ImageFile", "*png", "*gif", "*jpg"));
    	// getExtensionFilters() : 확장자명을 추가하거나 빼거나 아무거나 할 수 있음 / 선택할 파일의 필터역할을 할 수 있음
    	// 사용은 getExtensionFilters(new ExtensionFilters(""))로 한다
    	
    	// 스테이지 실행
    	// 선택한 파일을 파일클래스에 저장 File.toString() syso하면 볼 수 있음
    	File file =  chooser.showOpenDialog(stage);
    	
    	ProductRegisterPath.setText(file.toString()); // 파일의 실제 경로
    	
    	pimg = file.toURI().toString();
    	System.out.println(pimg); // 파일의 실제 경로를 컴퓨터가 알 수 있게 바이트로 바꾼 uri
    	
    	Image image = new Image(pimg);
    	ProductRegisterIMG.setImage(image);
    }
}
