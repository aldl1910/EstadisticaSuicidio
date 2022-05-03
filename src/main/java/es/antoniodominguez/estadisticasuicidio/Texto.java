package es.antoniodominguez.estadisticasuicidio;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Texto extends VBox{
    
    static Text text;
    
    public Texto(){
        this.setAlignment(Pos.CENTER);
        text = new Text();
        
        // https://www.desarrollo-web-br-bd.com/es/javafx/como-cambiar-el-color-del-texto-en-javafx-textfield/1046781022/
        this.setStyle("-fx-font: normal 18px 'serif' ");
        this.getChildren().add(text); 
        
    }   
}