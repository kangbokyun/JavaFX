package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import dao.BoardDAO;
import dao.MemberDAO;
import dao.ProductDAO;
import domain.Product;
import domain.ProductDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class TotalController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		totalmemberLabel.setText(MemberDAO.getMemberDAO().memberCount() + "");
		totalBoardLabel.setText(BoardDAO.getboardDAO().boardCount() + "");
		totalProductLabel.setText(ProductDAO.getProductDAO().productCount() + "");
		
		// �迭 ����
		XYChart.Series series = new XYChart.Series<>();
		
		// �迭 �̸�
		series.setName("��ǰ ��");
		
		ArrayList<ProductDate> productDates = ProductDAO.getProductDAO().productDateList();
		
		for(ProductDate productDate : ProductDAO.getProductDAO().productDateList()) {
			XYChart.Data data = new XYChart.Data<>(productDate.getDate(), productDate.getCount());
//			series.getData().add(new XYChart.Data<>(productDate.getDate(), productDate.getCount()));
			
			// ��� ����(�����̳� �ֱ� -> �׷��� �� �� ���� �κп� ����ǥ���ϱ�)
			AnchorPane ap = new AnchorPane();
			Label label = new Label(productDate.getCount() + "");
			label.setPadding(new Insets(10)); // �� ���� ���� ����
			ap.getChildren().add(label);
			data.setNode(ap);
			
			series.getData().add(data);
		}
		
		// y�� ����
		lc.getYAxis().setAutoRanging(false); // y�� �ڵ����� ����
		
		lc.getData().add(series);
		
		// �� ���� ����
		// ���� ��� ������ �� ���ٸ�
		if(productDates.get(productDates.size() - 1).getCount() > productDates.get(productDates.size() - 2).getCount()) {
			// ���� �����
			decreaseLabel.setVisible(false);
		} else {
			// ���� �����
			increaseLabel.setVisible(false);
		}
		
		//----------------------------------------------------------------------------------------------------------------------
		// Bar��Ʈ 
		//---------
		// �迭 ����
		XYChart.Series series2 = new XYChart.Series<>(); 
		
		// �迭 �̸�
		series2.setName("�Ƿ�");
		
		// �迭�� �� �ֱ�
		HashMap<String, Integer> map = ProductDAO.getProductDAO().productCategoryList();
		
		// ���� ū ī�װ� ã��
		String maxCategoryKey = " ";
		int max = 0;
		
		// ��Ʈ�� �迭 �ֱ�
		for(String key : map.keySet()) {
			if(map.get(key) > max) {
				max = map.get(key);
				maxCategoryKey = key;
			}
			maxCategoryLabel.setText(maxCategoryKey);
			XYChart.Data data = new XYChart.Data<>(key, map.get(key));
			series2.getData().add(data);
		}
		
		BC.getData().add(series2);
		
		//----------------------------------------------------------------------------------------------------------------------
		// ������Ʈ
		// ---------
		
		ObservableList<Product> products = ProductDAO.getProductDAO().productList();
		
		ObservableList<PieChart.Data> obList = FXCollections.observableArrayList();
		for(Product pro : products) {
			obList.add(new PieChart.Data(pro.getActivation(), 1));
		}
//		obList.add(new PieChart.Data("����", 10));
//		obList.add(new PieChart.Data("����", 5));
//		obList.add(new PieChart.Data("�Ź�", 15));
		
		PC.setData(obList);
		
		//----------------------------------------------------------------------------------------------------------------------
		
//		// ������Ʈ(�����͸� �־���� ��)
//		// ������Ʈ �޸� �Ҵ�
//		XYChart.Series series = new XYChart.Series<>();
//		
//		series.setName("��ǰ ��ϼ�");
//		
//		// ������
//		ObservableList<Product> products = ProductDAO.getProductDAO().productList();
//		
//		// ��¥�� ����
//		ArrayList<ProductDate> dates = new ArrayList<>();
//		for(Product product : products) {
//			String date = product.getP_date().split(" ")[0]; // ��¥�� ������ �Ŷ� ���� �ڿ� �ִ� �ð��� ���þ��� [0]��°�� �����ͼ� ��
//			
//			boolean dateCheck = true;
//			
//			// ��¥�� ī��Ʈ ��
//			for(int i = 0; i < dates.size(); i++) {
//				if(dates.get(i).getDate().equals(date)) {
//					dates.get(i).setCount(dates.get(i).getCount() + 1);
//					break;
//				}
//			}
//			if(dateCheck) {
//				dates.add(new ProductDate(date, 1));
//			}
//		}
//		
//		for(ProductDate date : dates) {
//			XYChart.Data data = new XYChart.Data<>(date.getDate() + "", date.getCount());
//			series.getData().add(data);
//		}
//		
//		lc.getData().add(series);
//--------------------------------------------------------------------------------------------------------------------------------------
//		// 1. �迪����
//		XYChart.Series series2 = new XYChart.Series<>();
//		
//		// 2. �迭�̸�
//		series2.setName("gd");
//		
//		// 3. ������ ����
//		XYChart.Data data = new XYChart.Data<>("1", 30);
//--------------------------------------------------------------------------------------------------------------------------------------
		// ��� ���ڵ� ���� Ȯ��
		// select count(*) from ���̺��
		
		// p_date(��¥) �������� ����
		// select substring_indx(product.p_date, ' ', 1) from product
		// * substring(�ʵ��, "���ر���", "�����ù�ȣ")
		// 											   2021-11-04 (1) / 15:13:12 (2)
		
		// �׷�
		// GROUP BY �ʵ�� -> �׷����� ����
		// �׷� ����
		// having �׷쳻���� < ---- > where �׷������
		
	}

	@FXML
	private Label totalmemberLabel;
	
	@FXML
	private Label totalBoardLabel;

	@FXML
	private Label totalProductLabel;

	@FXML
	private LineChart lc;
	
	@FXML
	private Label increaseLabel;

	@FXML
	private Label decreaseLabel;
	
	@FXML
	private BarChart BC;
	
	@FXML
	private Label maxCategoryLabel;

	@FXML
	private PieChart PC;
}
