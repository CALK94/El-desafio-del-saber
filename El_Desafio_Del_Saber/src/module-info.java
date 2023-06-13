module El_Desafio_Del_Saber {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	exports controller;
	
	
	opens application to javafx.graphics, javafx.fxml;
	opens view to javafx.graphics, javafx.fxml;
	opens controller to javafx.fxml;
}
