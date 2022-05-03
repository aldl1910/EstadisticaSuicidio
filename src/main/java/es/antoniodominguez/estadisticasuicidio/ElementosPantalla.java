package es.antoniodominguez.estadisticasuicidio;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class ElementosPantalla extends HBox {
    public ElementosPantalla(ArrayList listaPaises){
        this.setSpacing(40);
        
        // CREAMOS UN COMBOBOX CON LA LISTA DE PAISES
        ComboBox<String> comboBoxPaisSeleccionado = new ComboBox(FXCollections.observableList(listaPaises));
        comboBoxPaisSeleccionado.getSelectionModel().select("Selecciona un país");
        this.getChildren().add(comboBoxPaisSeleccionado);
        
        // CREAMOS UNA ARRAYLIST PARA LAS OPERACIONES QUE SE REALIZAN
        ArrayList<String> listaOperaciones = new ArrayList();
        listaOperaciones.add("Mínimo");
        listaOperaciones.add("Media");
        listaOperaciones.add("Máximo");
        
        // CREAMOS OTRO COMBOBOX CON EL CONTENIDO DE LA LISTA DE OPERACIONES
        ComboBox<String> comboBoxOperacion = new ComboBox(FXCollections.observableList(listaOperaciones));
        comboBoxOperacion.getSelectionModel().select("Selecciona una operación");
        this.getChildren().add(comboBoxOperacion);
        
        // CREAMOS UN BOTÓN QUE TENDRÁ LA ACCIÓN DE REALIZAR LA OPERACIÓN
        Button botonOperacion = new Button("Realizar operación");
        this.getChildren().add(botonOperacion);
        botonOperacion.setOnAction((t) -> {
            TratarCSV.ObtenerDatos(comboBoxPaisSeleccionado,comboBoxOperacion);            
        });
    }
}
