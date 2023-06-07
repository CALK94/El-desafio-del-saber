package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class ElDesafioDelSaberController {

    @FXML //anotacion definida para identificar y bautizar elemento en SceneBuilder
    private Label questionLabel;

    @FXML //anotacion definida para identificar y bautizar elemento en SceneBuilder
    private RadioButton option1RadioButton;

    @FXML //anotacion definida para identificar y bautizar elemento en SceneBuilder
    private RadioButton option2RadioButton;

    @FXML //anotacion definida para identificar y bautizar elemento en SceneBuilder
    private RadioButton option3RadioButton;

    @FXML //anotacion definida para identificar y bautizar elemento en SceneBuilder
    private Button submitButton;

    @FXML //anotacion definida para identificar y bautizar elemento en SceneBuilder
    private Label resultLabel;

    private ToggleGroup optionsGroup;		//Variable para la agrupacion de los Radio Button

    private int currentQuestionIndex = 0;	//Variable para realizar seguimiento del indice de la pregunta actual
    private int score = 0;					//Variable para realizar seguimiento del score actual

    private String[] questions = {			//matriz 1 almacena las preguntas
            "¿Cuál es la ciudad Colombiana conocida como \"La Ciudad de la Eterna Primavera\"?",
            "¿Cuál es el principal deporte de Colombia?",
            "¿Quién fue el primer presidente de Colombia?"
    };

    private String[][] options = {			//matriz 2 almacena las respuestas
            {"Bogotá", "Medellín", "Cali"},
            {"Patinaje", "Tejo", "Futbol"},
            {"Simón Bolívar", "Francisco de Paula Santander", "José Miguel Pey de Andrade"}
    };

    private int[] correctAnswers = {1, 2, 0};		//array almacena el indice de la opcion de la respuesta correcta para cada pregunta

    @FXML
    private void initialize() {		//metodo se llama cuando se carga la interfaz grafica y se configura anotacion definidas
    	questionLabel.setText(questions[0]);
    	option1RadioButton.setText(options[0][0]);
    	option2RadioButton.setText(options[0][1]);
    	option3RadioButton.setText(options[0][2]);
    	submitButton.setText("Enviar");
    	resultLabel.setText("");
        optionsGroup = new ToggleGroup();	//crea el objeto de la clase y luego se configura en los botones 1,2 y 3.
        option1RadioButton.setToggleGroup(optionsGroup); //es utilizado para agrupar los botones y asi solo se pueda seleccionar uno de ellos.
        option2RadioButton.setToggleGroup(optionsGroup);
        option3RadioButton.setToggleGroup(optionsGroup);
    }

    @FXML
    private void submitButtonClicked(ActionEvent event) {										//metodo se llama al darle click al boton "Enviar"
        RadioButton selectedRadioButton = (RadioButton) optionsGroup.getSelectedToggle();		
        if (selectedRadioButton != null) {
            int selectedAnswer = optionsGroup.getToggles().indexOf(selectedRadioButton);		
            if (selectedAnswer == correctAnswers[currentQuestionIndex]) {						// Si la respuesta es correcta 
                score++;																		// sumar al score
                resultLabel.setText("¡Respuesta correcta!");									// y arrojar mensaje
            } else {																			// Si no... 
                resultLabel.setText("Respuesta incorrecta. La respuesta correcta era: " +		//arrojar mensaje 
                        options[currentQuestionIndex][correctAnswers[currentQuestionIndex]]); 	// de la respuesta correcta
            }

            if (currentQuestionIndex < questions.length - 1) {									// Si aun no se han terminado las preguntas
                currentQuestionIndex++;															// sumar al contador actual de preguntas
                showQuestion();                                                                 // mostrar la pregunta segun el contador
            } else { 																			// si no... (si ya se terminaron las preguntas)
            	
            	if(selectedAnswer == correctAnswers[currentQuestionIndex]) {					// si la respuesta es correcta
            		resultLabel.setText("¡Respuesta correcta!" + "\n" + "Juego terminado. Puntuación: " + score + "/" + questions.length );	//mostrar este mensaje
            	} else {																		//si no
            		resultLabel.setText("Respuesta incorrecta. La respuesta correcta era: " +	//mostrar este mensaje
                            options[currentQuestionIndex][correctAnswers[currentQuestionIndex]] + "\n" +
                            "Juego terminado. Puntuación: " + score + "/" + questions.length);
            	}         	
            	
               
                submitButton.setDisable(true);
            }
            

            
        }
    }

    private void showQuestion() {	//este metodo actualiza los elementos de la interfaz grafica SceneBuilder
        questionLabel.setText(questions[currentQuestionIndex]);	//establece el texto en el label con la pregunta actual que obtiene del array
        option1RadioButton.setText(options[currentQuestionIndex][0]); //establece el texto en el Radio Button con la opcion de respuesta que obtiene de la matriz
        option2RadioButton.setText(options[currentQuestionIndex][1]);
        option3RadioButton.setText(options[currentQuestionIndex][2]);
        optionsGroup.selectToggle(null); //si seleccion un boton y luego otro, deselecciona el anterior
       
    }
}
