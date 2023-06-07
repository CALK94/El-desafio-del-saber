package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class ElDesafioDelSaberController {

    @FXML
    private Label questionLabel;

    @FXML
    private RadioButton option1RadioButton;

    @FXML
    private RadioButton option2RadioButton;

    @FXML
    private RadioButton option3RadioButton;

    @FXML
    private Button submitButton;

    @FXML
    private Label resultLabel;

    private ToggleGroup optionsGroup;

    private int currentQuestionIndex = 0;
    private int score = 0;

    private String[] questions = {
            "¿Cuál es la capital de Colombia?",
            "¿Cuál es el río más largo del mundo?",
            "¿Cuál es el año de la independencia de Estados Unidos?"
    };

    private String[][] options = {
            {"Bogotá", "Medellín", "Cali"},
            {"Amazonas", "Nilo", "Yangtsé"},
            {"1776", "1789", "1812"}
    };

    private int[] correctAnswers = {0, 1, 0};

    @FXML
    private void initialize() {
    	questionLabel.setText(questions[0]);
    	option1RadioButton.setText(options[0][0]);
    	option2RadioButton.setText(options[0][1]);
    	option3RadioButton.setText(options[0][2]);
    	submitButton.setText("Enviar");
    	resultLabel.setText("");
        optionsGroup = new ToggleGroup();
        option1RadioButton.setToggleGroup(optionsGroup);
        option2RadioButton.setToggleGroup(optionsGroup);
        option3RadioButton.setToggleGroup(optionsGroup);
    }

    @FXML
    private void submitButtonClicked(ActionEvent event) {
        RadioButton selectedRadioButton = (RadioButton) optionsGroup.getSelectedToggle();
        if (selectedRadioButton != null) {
            int selectedAnswer = optionsGroup.getToggles().indexOf(selectedRadioButton);
            if (selectedAnswer == correctAnswers[currentQuestionIndex]) {
                score++;
                resultLabel.setText("¡Respuesta correcta!");
            } else {
                resultLabel.setText("Respuesta incorrecta. La respuesta correcta era: " +
                        options[currentQuestionIndex][correctAnswers[currentQuestionIndex]]);
            }

            if (currentQuestionIndex < questions.length - 1) {
                currentQuestionIndex++;
                showQuestion();
            } else { 
            	
            	if(selectedAnswer == correctAnswers[currentQuestionIndex]) {
            		resultLabel.setText("¡Respuesta correcta!" + "\n" + "Juego terminado. Puntuación: " + score + "/" + questions.length );
            	} else {
            		resultLabel.setText("Respuesta incorrecta. La respuesta correcta era: " +
                            options[currentQuestionIndex][correctAnswers[currentQuestionIndex]] + "\n" +
                            "Juego terminado. Puntuación: " + score + "/" + questions.length);
            	}
            	
            	
                //resultLabel.setText(  + "\n" + "Juego terminado. Puntuación: " + score + "/" + questions.length );
                submitButton.setDisable(true);
            }
            

            
        }
    }

    private void showQuestion() {
        questionLabel.setText(questions[currentQuestionIndex]);
        option1RadioButton.setText(options[currentQuestionIndex][0]);
        option2RadioButton.setText(options[currentQuestionIndex][1]);
        option3RadioButton.setText(options[currentQuestionIndex][2]);
        optionsGroup.selectToggle(null);
       
    }
}
