package Day1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// 1. ��ӹޱ�
public class C2_Test extends Application{
	
	// 2. �������̵��ϱ�
	@Override
	public void start(Stage stage) throws Exception { // 3. ���������� ���ϱ�
		// 6. �����̳� �����
		VBox vbox = new VBox();
		// 7. ��Ʈ�� ����
		// 8. ��ư �����
		Button button = new Button(); // ��ư ����
		button.setText("�ݱ�"); // ��ư�� �� �ؽ�Ʈ
		button.setOnAction(e -> Platform.exit()); // ������ ����
		// ->(���ٽ�)
		
		// 10. �����̳ʿ� ��Ʈ�� �߰��ϱ�
		vbox.getChildren().add(button);
		
		// 11. �� ����� -> �����̳ʸ� ���� �ֱ�
		Scene scene = new Scene(vbox, 500, 500);
		
		// 12. ���� ���������� �ֱ�
		stage.setScene(scene);
		
		
		stage.show(); // 4. �������� ����
	}
	
	public static void main(String[] args) {
		launch(args); // 5. main�޼ҵ忡�� ��ŸƮ �޼ҵ� ȣ��
	}
}
