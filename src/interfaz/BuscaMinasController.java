package interfaz;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

import modelo.Buscaminas;

public class BuscaMinasController {

    @FXML
    private Button principiante;

    @FXML
    private Button intermedio;

    @FXML
    private Button experto;

    @FXML
    private Button pista;

    @FXML
    private Button resolver;

    @FXML
    private ScrollPane scrollpane;
    
    @FXML
    private GridPane gridpane;
    
    private Buscaminas buscaminas;
    
    @FXML
    /**
     * Permite inicializar todos los elementos y sus propiedades necesarias para ser mostradas al usuario
     */
    private void initialize() {
    	pista.setDisable(true);
    	resolver.setDisable(true);
    	gridpane = new GridPane();
    	gridpane.setAlignment(Pos.CENTER);
    	gridpane.setVgap(5.0);
    	gridpane.setHgap(5.0);
    }
    
    @FXML
    /**
     * Permite dar una pista al usuario sobre las casillas que puedan contener minas
     * @param event el evento disparado por el usuario
     */
    private void darPista(ActionEvent event) {
					
    	int[] rc = buscaminas.darPista();
    	
    	for (int k = 0; k < gridpane.getChildren().size(); k++) {
			Button mina = (Button) gridpane.getChildren().get(k);
			//Verifica cual fue la posición de la casilla que se destapó por pista para poder ser actualizada en la interfaz
			if(GridPane.getRowIndex(mina) == rc[0] && GridPane.getColumnIndex(mina) == rc[1]) {
				mina.setText(""+buscaminas.darCasillas()[GridPane.getRowIndex(mina)][GridPane.getColumnIndex(mina)].darValor());
			}
		}
    }

    @FXML
    /**
     * Permite generar un juego de nivel principiante
     * @param event el evento disparado por el usuario.
     */
    private void generarJuegoPrincipiante(ActionEvent event) {
    	buscaminas = new Buscaminas(1);
    	for (int i = 0; i < buscaminas.darCasillas().length; i++) {
    		for (int j = 0; j < buscaminas.darCasillas()[i].length; j++) {
				Button mina = new Button();
				//Crea los botones
				mina.setMaxSize(250.0, 250.0);
				mina.setStyle("-fx-background-color : SLATEGRAY");
				mina.setTextFill(Color.WHITE);
				//añade la casilla a la interfaz
				gridpane.add(mina, j, i);
				mina.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						
						int row = GridPane.getRowIndex(mina);
						int column = GridPane.getColumnIndex(mina);
						mina.setText(""+buscaminas.darCasillas()[GridPane.getRowIndex(mina)][GridPane.getColumnIndex(mina)].darValor());
						//Verifica si la casilla ya esta abierta
						if(!buscaminas.abrirCasilla(row, column)) {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setHeaderText("Information");
							alert.setContentText("La casilla ya esta abierta");
							alert.show();
						}	
						//Verifica si la casilla es una mina
						else if(buscaminas.darPerdio()) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setHeaderText("Information");
							alert.setContentText("Perdiste :((");
							alert.show();
							principiante.setDisable(true);							
						}
						//Verifica si la casilla no es una mina
						else if(buscaminas.gano()) {
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setHeaderText("Information");
							alert.setContentText("Ganaste!! :))");
							alert.show();
							principiante.setDisable(true);
						}
						
					}
    			});
			}
		}	
    	scrollpane.setContent(gridpane);
    	scrollpane.setFitToHeight(true);
    	scrollpane.setFitToWidth(true);
    	intermedio.setDisable(true);
    	experto.setDisable(true);
    	pista.setDisable(false);
    	resolver.setDisable(false);
    }
    
    @FXML
    /**
     * Permite generar un juego de nivel intermedio para el usuario
     * @param event el evento disparado por el usuario	
     */
    private void generarJuegoIntermedio(ActionEvent event) {
    	buscaminas = new Buscaminas(2);
    	for (int i = 0; i < buscaminas.darCasillas().length; i++) {
    		for (int j = 0; j < buscaminas.darCasillas()[i].length; j++) {
				Button mina = new Button();
				//Crea los botones
				mina.setMaxSize(250.0, 250.0);
				mina.setStyle("-fx-background-color : SLATEGRAY");
				mina.setTextFill(Color.WHITE);
				//añade la casilla a la interfaz
				gridpane.add(mina, j, i);
				mina.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						int row = GridPane.getRowIndex(mina);
						int column = GridPane.getColumnIndex(mina);
						mina.setText(""+buscaminas.darCasillas()[GridPane.getRowIndex(mina)][GridPane.getColumnIndex(mina)].darValor());
						//Verifica si la casilla ya esta abierta
						if(!buscaminas.abrirCasilla(row, column)) {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setHeaderText("Information");
							alert.setContentText("La casilla ya esta abierta");
							alert.show();
						}	
						//Verifica si la casilla es una mina
						else if(buscaminas.darPerdio()) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setHeaderText("Information");
							alert.setContentText("Perdiste :((");
							alert.show();
							intermedio.setDisable(true);
						}
						//Verifica si la casilla no es una mina
						else if(buscaminas.gano()) {
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setHeaderText("Information");
							alert.setContentText("Ganaste!! :))");
							alert.show();
							intermedio.setDisable(true);
						}
					}
    			});
			}
		}
    	scrollpane.setContent(gridpane);
    	scrollpane.setFitToHeight(true);
    	scrollpane.setFitToWidth(true);
    	principiante.setDisable(true);
    	experto.setDisable(true);
    	pista.setDisable(false);
    	resolver.setDisable(false);
    }
    
    @FXML
    /**
     * Permite generar un juego de nivel experto para el usuario
     * @param event el evento disparado por el usuario
     */
    private void generarJuegoExperto(ActionEvent event) {
    	buscaminas = new Buscaminas(3);
    	for (int i = 0; i < buscaminas.darCasillas().length; i++) {
    		for (int j = 0; j < buscaminas.darCasillas()[i].length; j++) {
				Button mina = new Button();
				//Crea los botones
				mina.setMaxSize(250.0, 250.0);
				mina.setStyle("-fx-background-color : SLATEGRAY");
				mina.setTextFill(Color.WHITE);
				//añade la casilla a la interfaz
				gridpane.add(mina, j, i);
				mina.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						int row = GridPane.getRowIndex(mina);
						int column = GridPane.getColumnIndex(mina);
						mina.setText(""+buscaminas.darCasillas()[GridPane.getRowIndex(mina)][GridPane.getColumnIndex(mina)].darValor());
						//Verifica si la casilla ya esta abierta
						if(!buscaminas.abrirCasilla(row, column)) {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setHeaderText("Information");
							alert.setContentText("La casilla ya esta abierta");
							alert.show();
						}
						//Verifica si la casilla es una mina
						else if(buscaminas.darPerdio()) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setHeaderText("Information");
							alert.setContentText("Perdiste :((");
							alert.show();
							experto.setDisable(true);
						}
						//Verifica si la casilla no es una mina
						else if(buscaminas.gano()) {
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setHeaderText("Information");
							alert.setContentText("Ganaste!! :))");
							alert.show();
							experto.setDisable(true);
						}
					}
    			});
			}
		}	
    	scrollpane.setContent(gridpane);
    	scrollpane.setFitToHeight(true);
    	scrollpane.setFitToWidth(true);
    	principiante.setDisable(true);
    	intermedio.setDisable(true);
    	pista.setDisable(false);
    	resolver.setDisable(false);
    }
    
    @FXML
    /**
     * Este metodo resuelve el juego para el usuario mostrandole toda la solución del tablero de minas
     * @param event el evento disparado por el usuario
     */
    private void resolverJuego(ActionEvent event) {
    	for (int i = 0; i < gridpane.getChildren().size(); i++) {
    		//Actualiza todas las casillas para ser mostradas al usuario
    		Button mina = (Button) gridpane.getChildren().get(i);
    		mina.setText(""+buscaminas.darCasillas()[GridPane.getRowIndex(mina)][GridPane.getColumnIndex(mina)].darValor());
		}
    	principiante.setDisable(true);
    	intermedio.setDisable(true);
    	experto.setDisable(true);
    	pista.setDisable(true);
    	resolver.setDisable(true);
    }    
}