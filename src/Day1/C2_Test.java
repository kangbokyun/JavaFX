package Day1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// 1. 상속받기
public class C2_Test extends Application{
	
	// 2. 오버라이드하기
	@Override
	public void start(Stage stage) throws Exception { // 3. 스테이지명 정하기
		// 6. 컨테이너 만들기
		VBox vbox = new VBox();
		// 7. 컨트롤 구성
		// 8. 버튼 만들기
		Button button = new Button(); // 버튼 생성
		button.setText("닫기"); // 버튼에 들어갈 텍스트
		button.setOnAction(e -> Platform.exit()); // 누르면 끈다
		// ->(람다식)
		
		// 10. 컨테이너에 컨트롤 추가하기
		vbox.getChildren().add(button);
		
		// 11. 씬 만들기 -> 컨테이너를 씬에 넣기
		Scene scene = new Scene(vbox, 500, 500);
		
		// 12. 씬을 스테이지에 넣기
		stage.setScene(scene);
		
		
		stage.show(); // 4. 스테이지 실행
	}
	
	public static void main(String[] args) {
		launch(args); // 5. main메소드에서 스타트 메소드 호출
	}
}
