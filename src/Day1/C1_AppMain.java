package Day1;

import javafx.application.Application;
import javafx.stage.Stage;

// module.info
// requires ����� ��� ��
// opens ��Ű���� to ����

// ��ӹޱ�
// start �޼ҵ� �������̵�
// ���������� ���ϱ� { ����������.show}
// main { launch }

public class C1_AppMain extends Application { // Aookication : javafx���� ���
	
	// �������̵� : start �޼ҵ�
	@Override
	// stage �̸� ���ϱ�
	public void start(Stage primaryStage) throws Exception {
		
		// stage ����
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
