module JavaFx {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
	opens Day1 to javafx.graphics, javafx.fxml;
	opens Day2 to javafx.graphics, javafx.fxml;
	opens Controller to javafx.graphics, javafx.fxml;
	opens domain to mail, activation, javafx.base;
	opens dao to javafx.graphics, javafx.fxml;
}
