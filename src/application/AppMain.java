package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class AppMain extends Application {
	@Override
	public void start(Stage stage) throws Exception {
			Parent parent = FXMLLoader.load(getClass().getResource("/Day1/test2.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("can't");
			
			// 스테이지 아이콘
			// 1. 이미지 불러오기
			Image image = new Image("file:C:\\Users\\ez201206\\Desktop\\KakaoTalk_20211028_150125977.png");
			stage.getIcons().add(image);
			
			stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
