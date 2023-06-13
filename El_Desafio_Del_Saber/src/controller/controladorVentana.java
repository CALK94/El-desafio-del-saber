package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.util.Duration;
import javafx.scene.control.TextField;

public class controladorVentana {
	
	@FXML //anotacion definida para identificar y bautizar elemento en SceneBuilder
	private Label nombreLabel;
	
	@FXML //anotacion definida para identificar y bautizar elemento en SceneBuilder
	private TextField nombreUsuarioTextField;
	
	@FXML //anotacion definida para identificar y bautizar elemento en SceneBuilder
	private Label edadLabel;

	@FXML //anotacion definida para identificar y bautizar elemento en SceneBuilder
	private TextField edadTextField;
	
	@FXML //anotacion definida para identificar y bautizar elemento en SceneBuilder
	private Button iniciarButton;
	
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

    private ToggleGroup grupoRespuestas;				//Variable para la agrupacion de los Radio Button
    private String nombreUsuario;						//Variable para capturar el nombre del Usuario
    private int edadUsuario;							//Variable para capturar la edad del Usuario
    private int indicePreguntaActualMenor18 = 0;		//Variable para realizar seguimiento del indice de la pregunta actual para menores de 18
    private int indicePreguntaActualMayor18 = 0;		//Variable para realizar seguimiento del indice de la pregunta actual para mayores de 18
    private int puntaje = 0;							//Variable para realizar seguimiento del score actual
    private Timeline timer;
    
    private String[] preguntasMayor18 = {				//matriz 1 almacena las preguntas para Mayores de 18
            "¿Cuál es la ciudad Colombiana conocida como \"La Ciudad de la Eterna Primavera\"?",
            "¿Cuál es el principal deporte de Colombia?",
            "¿Quién fue el primer presidente de Colombia?",
            "¿Cuantos departamentos tiene Colombia?",
            "¿Cual es la flor nacional de Colombia?",
            "¿Cual es el plato tipico de de Boyaca?",
            "¿Cual es el centro \"ombligo\" de Colombia?",
            "¿Cual es el baile tipico de la region de la Orinoquia de Colombia?",
            "¿Cuando fue el grito de independecia de Colombia?",
            "¿Donde se celebra el Carnaval de Negros y Blancos?",
            "¿Cual es el departamento mas grande de Colombia?",
         	"¿Cual es el rio de mayor longitud de Colombia?"               
   };
    
    private String[] preguntasMenor18 = {					//matriz 2 almacena las preguntas para Menores de 18
    		"¿Cuál es la capital del departamento del Atlantico?",
    		"¿Cuál es el ave nacional de Colombia?",
    		"¿Cuál es la capital del departamento de Bolivar?",
    		"¿Cuál es la capital del departamento de Antioquia?",
    		"¿Donde esta ubicado el parque acuatico Piscilago?",
    		"¿Cuál es el principal deporte de Colombia?",
    		"¿Cuál es la capital del departamento del Amazonas?",
    		"¿Que dia se celebra el dia de la independencia de Colombia?",
    		"¿Con cuantos paises comparte Colombia fronteras terrestres?",
    		"¿Cual es el equipo de futbol Colombiano con mas ligas ganadas?",
    		"¿Cuantos departamentos tiene Colombia?",
    		"¿Cuantas regiones naturales tiene Colombia?",
   };

    private String[][] opcionesRespuestaMayor18 = {			//matriz 3 almacena las respuestas para mayores de 18
            {"Bogotá", "Medellín", "Cali"},
            {"Patinaje", "Tejo", "Futbol"},
            {"Simón Bolívar", "Francisco de Paula Santander", "José Miguel Pey de Andrade"},
            {"28", "32", "30"},
            {"Heliconia", "Flor de azucar", "La orquidea"},
            {"Cocido Boyacense", "Caldo de costilla", "Cuchuco con Espinazo"},
            {"Puerto Lopez/Meta", "Bogota/Cundinamarca", "Ibague/Tolima"},
            {"Bambuco", "Cumbia", "Joropo"},
            {"20 de Julio de 1814", "20 de Julio de 1810", "07 de Agosto 1820"},
            {"Manizales/Caldas", "Barranquilla/Atlantico", "Pasto/Nariño"},
            {"Amazonas", "Vichada", "Meta"},
            {"Rio Amazonas", "Rio Magdalena", "Rio Cauca"}            
    };
    
    private String[][] opcionesRespuestaMenor18 = {			//matriz 4 almacena las respuestas para menores de 18
    		 {"Cartagena", "Santa Marta", "Barranquilla"},
             {"El Condor", "Pajaro Carpintero", "Flamenco"},
             {"San Andres", "Cartagena", "Valledupar"},
             {"Manizales", "Medellin", "Armenia"},
             {"Girardot", "Villavicencio", "Melgar"},
             {"Patinaje", "Tejo", "Futbol"},
             {"Leticia", "Popayan", "Florencia"},
             {"20 de julio", "07 de agosto", "01 de mayo"},
             {"4", "5", "6"},
             {"America", "Millonarios", "Nacional"},
             {"28", "32", "30"},  
             {"6", "5", "7"} 
    };

    private int[] respuestasCorrectasMayor18 = {1, 2, 0, 1, 2, 0, 0, 2, 1, 2, 0, 1};		//array 1 almacena el indice de la opcion de la respuesta correcta para cada pregunta mayor 18

    private int[] respuestasCorrectasMenor18 = {2, 0, 1, 1, 2, 2, 0, 0, 1, 2, 1, 0 };		//array 2 almacena el indice de la opcion de la respuesta correcta para cada pregunta menor 18
    	
    @FXML
    private void initialize() {					//metodo se llama cuando se carga la interfaz grafica y se configura anotacion definidas
    	preguntaLabel.setVisible(false);		//lo vuelve invisble para que solo se vea el campo de Usuario y edad
        enviarButton.setVisible(false);			
        respuestaOpcion1RadioButton.setVisible(false);
        respuestaOpcion2RadioButton.setVisible(false);
        respuestaOpcion3RadioButton.setVisible(false);
    	nombreUsuarioTextField.setText("");
    	edadTextField.setText("");
    	iniciarButton.setText("Iniciar");
    	preguntaLabel.setText(preguntasMayor18[0]);
    	respuestaOpcion1RadioButton.setText(opcionesRespuestaMenor18[0][0]);
    	respuestaOpcion2RadioButton.setText(opcionesRespuestaMenor18[0][1]);
    	respuestaOpcion3RadioButton.setText(opcionesRespuestaMenor18[0][2]);
    	respuestaOpcion1RadioButton.setText(opcionesRespuestaMayor18[0][0]);
    	respuestaOpcion2RadioButton.setText(opcionesRespuestaMayor18[0][1]);
    	respuestaOpcion3RadioButton.setText(opcionesRespuestaMayor18[0][2]);
    	enviarButton.setText("Enviar");
    	resultadoLabel.setText("");
    	grupoRespuestas = new ToggleGroup();						//crea el objeto de la clase y luego se configura en los botones 1,2 y 3.
        respuestaOpcion1RadioButton.setToggleGroup(grupoRespuestas); //es utilizado para agrupar los botones y asi solo se pueda seleccionar uno de ellos.
        respuestaOpcion2RadioButton.setToggleGroup(grupoRespuestas);
        respuestaOpcion3RadioButton.setToggleGroup(grupoRespuestas);
        timer = new Timeline(new KeyFrame(Duration.seconds(3), this::temporizadorTerminado));	//se crea un objeto "Timeline" que inicializa en 12s y llama al metodo temporizadorTerminado cuando el temporizador finaliza
        timer.setCycleCount(1);		//se ejecutaa una sola vez, y luego hara las acciones del metodo temporizadorTerminado														
    }
    
    @FXML
    private void iniciarButtonClicked(ActionEvent event) { //metodo al darle click al boton "iniciar"
        nombreUsuario = nombreUsuarioTextField.getText(); //captura el nombre que se ingrese en la variable nombreUsuario
        String edad = edadTextField.getText();			//caputra la edad que se ingrese en la variable edad
        edadUsuario = Integer.parseInt(edad);			//convierte la variable String edad a int
        mostrarPregunta();								//llama al metodo mostrarPregunta
    }
    
    private void temporizadorTerminado(ActionEvent event) { //metodo que al terminar el temporizador se ejecuta
    	if (edadUsuario < 18) {
    		resultadoLabel.setText("Tiempo agotado. La respuesta correcta era: " +
                    opcionesRespuestaMenor18[indicePreguntaActualMenor18][respuestasCorrectasMenor18[indicePreguntaActualMenor18]]);
    	} else {
    		resultadoLabel.setText("Tiempo agotado. La respuesta correcta era: " +
                    opcionesRespuestaMayor18[indicePreguntaActualMayor18][respuestasCorrectasMayor18[indicePreguntaActualMayor18]]);
    	}
    	
    	if (edadUsuario < 18) {
    		if (indicePreguntaActualMenor18 < preguntasMenor18.length - 1) {
                indicePreguntaActualMenor18++;
                mostrarPregunta();
            } else {
                resultadoLabel.setText(resultadoLabel.getText() + "\n" +
                        "Juego terminado " + nombreUsuario + ", Puntuación: " + puntaje + "/" + preguntasMenor18.length);
                enviarButton.setDisable(true);
            }
    	} else {
    		if (indicePreguntaActualMayor18 < preguntasMayor18.length - 1) {
    			indicePreguntaActualMayor18++;
                mostrarPregunta();
    		} else {
    			resultadoLabel.setText(resultadoLabel.getText() + "\n" +
                        "Juego terminado " + nombreUsuario + ", Puntuación: " + puntaje + "/" + preguntasMayor18.length);
                enviarButton.setDisable(true);
    		}
    	}
    	
    }

    @FXML
    private void enviarButtonClicked(ActionEvent event) {													//metodo se llama al darle click al boton "Enviar"
        RadioButton selectedRadioButton = (RadioButton) grupoRespuestas.getSelectedToggle();		
        if (selectedRadioButton != null) {																	// se asegura que se seleccione alguna opcion
            int respuestaSeleccionada = grupoRespuestas.getToggles().indexOf(selectedRadioButton);			//se obtiene el indice de la respuesta seleccionada y se almacena en respuestaSeleccionada
            if (edadUsuario < 18) {																			//si... el usuario es menor de 18 años
            	if (respuestaSeleccionada == respuestasCorrectasMenor18[indicePreguntaActualMenor18]) {		// si... la respuesta es correcta 
                	puntaje++;																				// sumar al puntaje
                    resultadoLabel.setText("¡Respuesta correcta!");											// y monstrar mensaje
                } else {																					// Si no... 
                	resultadoLabel.setText("Respuesta incorrecta. La respuesta correcta era: " +			//mostrar mensaje 
                			opcionesRespuestaMenor18[indicePreguntaActualMenor18][respuestasCorrectasMenor18[indicePreguntaActualMenor18]]); 	// de la respuesta correctas del array 2
                }            	
            	 if (indicePreguntaActualMenor18 < preguntasMenor18.length - 1) {							// Si... aun no se han terminado las preguntas
             		indicePreguntaActualMenor18++;															// sumar al contador indicePreguntaActualMenor18 (de esta forma se van cambiando las preguntas)
                 	mostrarPregunta();                                                             			// mostrar la pregunta segun el contador indicePreguntaActualMenor18
                 } else { 																					// si no... (si ya se terminaron las preguntas)
                 	if(respuestaSeleccionada == respuestasCorrectasMenor18[indicePreguntaActualMenor18]) {	// si la respuesta es correcta
                 		resultadoLabel.setText("¡Respuesta correcta!" + "\n" + "Juego terminado " + nombreUsuario + ", Puntuación: " + puntaje + "/" + preguntasMenor18.length );	//mostrar este mensaje
                 	} else {																			//si no
                 		resultadoLabel.setText("Respuesta incorrecta. La respuesta correcta era: " +	//mostrar este mensaje
                 				opcionesRespuestaMenor18[indicePreguntaActualMenor18][respuestasCorrectasMenor18[indicePreguntaActualMenor18]] + "\n" +
                                 "Juego terminado " + nombreUsuario + ", tu Puntuación: " + puntaje + "/" + preguntasMenor18.length);
                 	}	
                 	enviarButton.setDisable(true);
                 }              
            	} else {																						//si... el usuario no es menor de 18 (es mayor 18años)
            		if (respuestaSeleccionada == respuestasCorrectasMayor18[indicePreguntaActualMayor18]) {		// Si la respuesta es correcta 
            			puntaje++;																				// sumar al puntaje
            			resultadoLabel.setText("¡Respuesta correcta!");											// y arrojar mensaje
            		} else {																					// Si no... 
            			resultadoLabel.setText("Respuesta incorrecta. La respuesta correcta era: " +			//arrojar mensaje 
                		opcionesRespuestaMayor18[indicePreguntaActualMayor18][respuestasCorrectasMayor18[indicePreguntaActualMayor18]]); 	// de la respuesta correcta
            		}
            	
            		if (indicePreguntaActualMayor18 < preguntasMayor18.length - 1) {							// Si... aun no se han terminado las preguntas
            			indicePreguntaActualMayor18++;															// sumar al contador indicePreguntaActualMayor18 (de esta forma se van cambiando las preguntas)
            			mostrarPregunta();                                                           		    // mostrar la pregunta segun el contador indicePreguntaActualMayor18
            		} else { 																					// si no... (si ya se terminaron las preguntas)
            			if(respuestaSeleccionada == respuestasCorrectasMayor18[indicePreguntaActualMayor18]) {	// si la respuesta es correcta
                 		resultadoLabel.setText("¡Respuesta correcta!" + "\n" + "Juego terminado " + nombreUsuario + ", Puntuación: " + puntaje + "/" + preguntasMayor18.length );	//mostrar este mensaje
                 	} else {																			//si no
                 		resultadoLabel.setText("Respuesta incorrecta. La respuesta correcta era: " +	//mostrar este mensaje
                 				opcionesRespuestaMayor18[indicePreguntaActualMayor18][respuestasCorrectasMayor18[indicePreguntaActualMayor18]] + "\n" +
                                 "Juego terminado " + nombreUsuario + ", tu Puntuación: " + puntaje + "/" + preguntasMayor18.length);
                 	}         	
                 	enviarButton.setDisable(true);
                 }
            }      
          
        }
    }

    private void mostrarPregunta() {	//este metodo actualiza los elementos de la interfaz grafica SceneBuilder
    	nombreUsuarioTextField.setVisible(false); //luedo de ingresar Usuario, edad y darle click al boton "iniciar" se vuelven invisibles.
    	nombreLabel.setVisible(false);
        edadTextField.setVisible(false);
        edadLabel.setVisible(false);
        iniciarButton.setVisible(false);
        preguntaLabel.setVisible(true);
        respuestaOpcion1RadioButton.setVisible(true); //se vuelven visibles despues de darle click al boton "iniciar"
        respuestaOpcion2RadioButton.setVisible(true);
        respuestaOpcion3RadioButton.setVisible(true);
        enviarButton.setVisible(true);
    	nombreUsuarioTextField.setText("");
    	edadTextField.setText("");
    	
    	if (edadUsuario < 18) {	//si... el usuario es menor de 18
    		preguntaLabel.setText(preguntasMenor18[indicePreguntaActualMenor18]);	//establece el texto en el label con la pregunta actual que obtiene del array
            respuestaOpcion1RadioButton.setText(opcionesRespuestaMenor18[indicePreguntaActualMenor18][0]); //establece el texto en el Radio Button con la opcion de respuesta que obtiene de la matriz
            respuestaOpcion2RadioButton.setText(opcionesRespuestaMenor18[indicePreguntaActualMenor18][1]);
            respuestaOpcion3RadioButton.setText(opcionesRespuestaMenor18[indicePreguntaActualMenor18][2]);
            grupoRespuestas.selectToggle(null); //si selecciona un boton y luego otro, deselecciona el anterior
    	} else { //si no... es menor de 18 (mayor 18)
    		preguntaLabel.setText(preguntasMayor18[indicePreguntaActualMayor18]);	//establece el texto en el label con la pregunta actual que obtiene del array
            respuestaOpcion1RadioButton.setText(opcionesRespuestaMayor18[indicePreguntaActualMayor18][0]); //establece el texto en el Radio Button con la opcion de respuesta que obtiene de la matriz
            respuestaOpcion2RadioButton.setText(opcionesRespuestaMayor18[indicePreguntaActualMayor18][1]);
            respuestaOpcion3RadioButton.setText(opcionesRespuestaMayor18[indicePreguntaActualMayor18][2]);
            grupoRespuestas.selectToggle(null); //si selecciona un boton y luego otro, deselecciona el anterior
    	}    	
    	timer.playFromStart(); //inicia el temporizador en 0 cada vez que llamen el metodo mostrarPregunta
      }
        
}
