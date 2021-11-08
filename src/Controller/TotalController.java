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
		
		// 계열 생성
		XYChart.Series series = new XYChart.Series<>();
		
		// 계열 이름
		series.setName("제품 수");
		
		ArrayList<ProductDate> productDates = ProductDAO.getProductDAO().productDateList();
		
		for(ProductDate productDate : ProductDAO.getProductDAO().productDateList()) {
			XYChart.Data data = new XYChart.Data<>(productDate.getDate(), productDate.getCount());
//			series.getData().add(new XYChart.Data<>(productDate.getDate(), productDate.getCount()));
			
			// 노드 설정(컨테이너 넣기 -> 그래프 내 점 찍힌 부분에 증감표시하기)
			AnchorPane ap = new AnchorPane();
			Label label = new Label(productDate.getCount() + "");
			label.setPadding(new Insets(10)); // 라벨 안쪽 여백 설정
			ap.getChildren().add(label);
			data.setNode(ap);
			
			series.getData().add(data);
		}
		
		// y축 설정
		lc.getYAxis().setAutoRanging(false); // y축 자동설정 끄기
		
		lc.getData().add(series);
		
		// 라벨 증감 설정
		// 전일 대비 오늘이 더 높다면
		if(productDates.get(productDates.size() - 1).getCount() > productDates.get(productDates.size() - 2).getCount()) {
			// 감소 숨기기
			decreaseLabel.setVisible(false);
		} else {
			// 증가 숨기기
			increaseLabel.setVisible(false);
		}
		
		//----------------------------------------------------------------------------------------------------------------------
		// Bar차트 
		//---------
		// 계열 생성
		XYChart.Series series2 = new XYChart.Series<>(); 
		
		// 계열 이름
		series2.setName("의류");
		
		// 계열에 값 넣기
		HashMap<String, Integer> map = ProductDAO.getProductDAO().productCategoryList();
		
		// 가장 큰 카테고리 찾기
		String maxCategoryKey = " ";
		int max = 0;
		
		// 차트에 계열 넣기
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
		// 원형차트
		// ---------
		
		ObservableList<Product> products = ProductDAO.getProductDAO().productList();
		
		ObservableList<PieChart.Data> obList = FXCollections.observableArrayList();
		for(Product pro : products) {
			obList.add(new PieChart.Data(pro.getActivation(), 1));
		}
//		obList.add(new PieChart.Data("상의", 10));
//		obList.add(new PieChart.Data("하의", 5));
//		obList.add(new PieChart.Data("신발", 15));
		
		PC.setData(obList);
		
		//----------------------------------------------------------------------------------------------------------------------
		
//		// 라인차트(데이터를 넣어줘야 함)
//		// 라인차트 메모리 할당
//		XYChart.Series series = new XYChart.Series<>();
//		
//		series.setName("제품 등록수");
//		
//		// 데이터
//		ObservableList<Product> products = ProductDAO.getProductDAO().productList();
//		
//		// 날짜로 구분
//		ArrayList<ProductDate> dates = new ArrayList<>();
//		for(Product product : products) {
//			String date = product.getP_date().split(" ")[0]; // 날짜만 가져올 거라 띄어쓰기 뒤에 있는 시간은 선택안함 [0]번째만 가져와서 씀
//			
//			boolean dateCheck = true;
//			
//			// 날짜로 카운트 셈
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
//		// 1. 계역생성
//		XYChart.Series series2 = new XYChart.Series<>();
//		
//		// 2. 계열이름
//		series2.setName("gd");
//		
//		// 3. 데이터 생성
//		XYChart.Data data = new XYChart.Data<>("1", 30);
//--------------------------------------------------------------------------------------------------------------------------------------
		// 모든 레코드 개수 확인
		// select count(*) from 테이블명
		
		// p_date(날짜) 기준으로 분해
		// select substring_indx(product.p_date, ' ', 1) from product
		// * substring(필드명, "분해기준", "가져올번호")
		// 											   2021-11-04 (1) / 15:13:12 (2)
		
		// 그룹
		// GROUP BY 필드명 -> 그룹으로 묶임
		// 그룹 조건
		// having 그룹내조건 < ---- > where 그룹외조건
		
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
