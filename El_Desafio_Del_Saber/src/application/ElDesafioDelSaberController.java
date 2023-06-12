package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class ElDesafioDelSaberController {

    @FXML //anotacion definida para identificar y bautizar elemento en SceneBuilder
    private Label preguntaLabel;

    @FXML //anotacion definida para identificar y bautizar elemento en SceneBuilder
    private RadioButton respuestaOpcion1RadioButton;

    @FXML //anotacion definida para identificar y bautizar elemento en SceneBuilder
    private RadioButton respuestaOpcion2RadioButton;

    @FXML //anotacion definida para identificar y bautizar elemento en SceneBuilder
    private RadioButton respuestaOpcion3RadioButton;

    @FXML //anotacion definida para identificar y bautizar elemento en SceneBuilder
    private Button enviarButton;

    @FXML //anotacion definida para identificar y bautizar elemento en SceneBuilder
    private Label resultadoLabel;

    private ToggleGroup grupoRespuestas;		//Variable para la agrupacion de los Radio Button

    private int indicePreguntaActual = 0;		//Variable para realizar seguimiento del indice de la pregunta actual
    private int puntaje = 0;					//Variable para realizar seguimiento del score actual

    private String[] preguntas = {			//matriz 1 almacena las preguntas
            "¿Cuál es la ciudad Colombiana conocida como \"La Ciudad de la Eterna Primavera\"?",
            "¿Cuál es el principal deporte de Colombia?",
            "¿Quién fue el primer presidente de Colombia?",
            "¿Cuantos departamentos tiene Colombia?"
            
    };

    private String[][] opcionesRespuesta = {			//matriz 2 almacena las respuestas
            {"Bogotá", "Medellín", "Cali"},
            {"Patinaje", "Tejo", "Futbol"},
            {"Simón Bolívar", "Francisco de Paula Santander", "José Miguel Pey de Andrade"},
            {"28", "32", "30"}
            
    };

    private int[] respuestasCorrectas = {1, 2, 0, 1};		//array almacena el indice de la opcion de la respuesta correcta para cada pregunta

    @FXML
    private void initialize() {		//metodo se llama cuando se carga la interfaz grafica y se configura anotacion definidas
    	preguntaLabel.setText(preguntas[0]);
    	respuestaOpcion1RadioButton.setText(opcionesRespuesta[0][0]);
    	respuestaOpcion2RadioButton.setText(opcionesRespuesta[0][1]);
    	respuestaOpcion3RadioButton.setText(opcionesRespuesta[0][2]);
    	enviarButton.setText("Enviar");
    	resultadoLabel.setText("");
    	grupoRespuestas = new ToggleGroup();	//crea el objeto de la clase y luego se configura en los botones 1,2 y 3.
        respuestaOpcion1RadioButton.setToggleGroup(grupoRespuestas); //es utilizado para agrupar los botones y asi solo se pueda seleccionar uno de ellos.
        respuestaOpcion2RadioButton.setToggleGroup(grupoRespuestas);
        respuestaOpcion3RadioButton.setToggleGroup(grupoRespuestas);
    }

    @FXML
    private void enviarButtonClicked(ActionEvent event) {												//metodo se llama al darle click al boton "Enviar"
        RadioButton selectedRadioButton = (RadioButton) grupoRespuestas.getSelectedToggle();		
        if (selectedRadioButton != null) {																// se asegura que se seleccion alguna opcion
            int respuestaSeleccionada = grupoRespuestas.getToggles().indexOf(selectedRadioButton);		//se obtiene el indice de la respuesta seleccionada y se almacena en respuestaSeleccionada
            if (respuestaSeleccionada == respuestasCorrectas[indicePreguntaActual]) {					// Si la respuesta es correcta 
            	puntaje++;																				// sumar al puntaje
                resultadoLabel.setText("¡Respuesta correcta!");											// y arrojar mensaje
            } else {																					// Si no... 
            	resultadoLabel.setText("Respuesta incorrecta. La respuesta correcta era: " +			//arrojar mensaje 
            			opcionesRespuesta[indicePreguntaActual][respuestasCorrectas[indicePreguntaActual]]); 	// de la respuesta correcta
            }

            if (indicePreguntaActual < preguntas.length - 1) {									// Si aun no se han terminado las preguntas
            	indicePreguntaActual++;															// sumar al contador indicePreguntaActual (de esta forma se van cambiando las preguntas)
            	mostrarPregunta();                                                              // mostrar la pregunta segun el contador indicePreguntaActual
            } else { 																			// si no... (si ya se terminaron las preguntas)
            	
            	if(respuestaSeleccionada == respuestasCorrectas[indicePreguntaActual]) {					// si la respuesta es correcta
            		resultadoLabel.setText("¡Respuesta correcta!" + "\n" + "Juego terminado. Puntuación: " + puntaje + "/" + preguntas.length );	//mostrar este mensaje
            	} else {																			//si no
            		resultadoLabel.setText("Respuesta incorrecta. La respuesta correcta era: " +	//mostrar este mensaje
            				opcionesRespuesta[indicePreguntaActual][respuestasCorrectas[indicePreguntaActual]] + "\n" +
                            "Juego terminado. Puntuación: " + puntaje + "/" + preguntas.length);
            	}         	
            	
               
            	enviarButton.setDisable(true);
            }
            

            
        }
    }

    private void mostrarPregunta() {	//este metodo actualiza los elementos de la interfaz grafica SceneBuilder
    	preguntaLabel.setText(preguntas[indicePreguntaActual]);	//establece el texto en el label con la pregunta actual que obtiene del array
        respuestaOpcion1RadioButton.setText(opcionesRespuesta[indicePreguntaActual][0]); //establece el texto en el Radio Button con la opcion de respuesta que obtiene de la matriz
        respuestaOpcion2RadioButton.setText(opcionesRespuesta[indicePreguntaActual][1]);
        respuestaOpcion3RadioButton.setText(opcionesRespuesta[indicePreguntaActual][2]);
        grupoRespuestas.selectToggle(null); //si selecciona un boton y luego otro, deselecciona el anterior
       
    }
}
