package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ElDesafioDelSaberApp extends Application { //JavaFX

    @Override
    public void start(Stage primaryStage) throws Exception {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Ventana.fxml"));	//se utiliza para cargar el archivo FXML que define la apariencia de la interfaz grafica
    	Parent root = loader.load();	//se carga el archivo y se obtiene el nodo raiz
        primaryStage.setTitle("El desafio del saber");	//titulo de la ventana principal
        primaryStage.setScene(new Scene(root));	//se crena crea una nueva escena con el nodo de raiz
        primaryStage.show();	//esto hace que la vetana se haga visible para el usuario
    }

    public static void main(String[] args) {
        launch(args);
    }
}
