package 정리;

public class 정리1 {
	// javafx sdk 다운로드
	// 빌드패스에서 lib만 싹 긁어와서 저장
	// 모듈인포에 패키지명을 어플리케이션이라 쓰인 곳에 추가
	
	// ------------------------------------------------------------
	// login.fxml : 씬빌더에서 만든 파일
	// LoginController : 자바에서 만든 파일
	// fx:id 정하기
	// -> 씬빌더의 각 컨테이너 혹은 컨트롤에 이름과 메소드명(액션)
	// fxml과 controller 연결
	// -> fxml 파일 내 첫번째 줄에 fx:controller = "패키지명.컨트롤러"
	

	// fx클래스
	// 1. extends Application
	// 2. Stage
	// 3. Parent parent = FXMLLoader.load(getClass().getResource("파일명")) -> 동일 패키지는 파일명만 쓰면 되고 아니면 경로 써줘야 함
	// 4. Scene scene = new Scene(parent);
	// 5. stage.
}
