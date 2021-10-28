package Day1;

import javafx.application.Application;
import javafx.stage.Stage;

// module.info
// requires 사용할 모듈 명
// opens 패키지명 to 모듈명

// 상속받기
// start 메소드 오버라이딩
// 스테이지명 정하기 { 스테이지명.show}
// main { launch }

public class C1_AppMain extends Application { // Aookication : javafx에게 상속
	
	// 오버라이딩 : start 메소드
	@Override
	// stage 이름 정하기
	public void start(Stage primaryStage) throws Exception {
		
		// stage 실행
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
