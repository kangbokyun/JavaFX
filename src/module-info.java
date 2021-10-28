module JavaFx {
	requires javafx.controls;
	requires javafx.fxml;

	
	opens application to javafx.graphics, javafx.fxml;
	opens Day1 to javafx.graphics, javafx.fxml;
	opens Controller to javafx.graphics, javafx.fxml;
}
