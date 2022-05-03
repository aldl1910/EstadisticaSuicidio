package es.antoniodominguez.estadisticasuicidio;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage stage) {
        VBox paneRoot;
        paneRoot = new VBox();
        var scene = new Scene(paneRoot, 640, 480);
        stage.setScene(scene);
        stage.show();
        paneRoot.setAlignment(Pos.TOP_CENTER);
        paneRoot.setSpacing(200);
        
        // LLAMAMOS A LA CLASE TRATARCSV
        TratarCSV tratarCSV = new TratarCSV();
        // LO AÑADIMOS A LA PANTALLA
        paneRoot.getChildren().add(tratarCSV);
        
        // LLAMAMOS A LA CLASE TEXTO
        Texto texto = new Texto();
        // LO AÑADIMOS A LA PANTALLA
        paneRoot.getChildren().add(texto);
    }

    public static void main(String[] args) {
        launch();
    }

}