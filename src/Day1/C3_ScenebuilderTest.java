package Day1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class C3_ScenebuilderTest extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// 씬빌더에서 작업한 fxml을 불러오기
		Parent parent = FXMLLoader.load(getClass().getResource("test1.fxml"));
		
		// parent -> 씬에 넣기
		Scene scene = new Scene(parent);
		
		// scene을 stage에 넣기
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
