package interfaz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{
	
		@Override
		public void start(Stage stage) throws Exception{
			Parent root =
					FXMLLoader.load(getClass().getResource("BuscaMinas.fxml"));
	
			Scene scene = new Scene(root);
			stage.setTitle("BuscaMinas");
			stage.setResizable(false);
			stage.setScene(scene);
			
			Image icon = new Image("interfaz/imagenes/icon.jpg");
			stage.getIcons().add(icon);
			stage.show();
		}
	
	public static void main(String[] args) {
		launch(args);
	}

}

