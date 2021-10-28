package Day1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class C3_ScenebuilderTest extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// ���������� �۾��� fxml�� �ҷ�����
		Parent parent = FXMLLoader.load(getClass().getResource("test1.fxml"));
		
		// parent -> ���� �ֱ�
		Scene scene = new Scene(parent);
		
		// scene�� stage�� �ֱ�
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
