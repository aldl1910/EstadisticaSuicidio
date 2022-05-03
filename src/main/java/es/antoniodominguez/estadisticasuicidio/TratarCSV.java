package es.antoniodominguez.estadisticasuicidio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class TratarCSV extends HBox{
    
    static RateSuicidios rateSuicidios1 = new RateSuicidios();

    // ARRAYS:
    ArrayList listaPaises = new ArrayList();
    static ArrayList arrayPorcentaje = new ArrayList();
    static ArrayList arrayAnnos = new ArrayList();
    
    public TratarCSV(){
        this.setAlignment(Pos.CENTER);
        // SELECCIONAMOS EL FICHERO QUE VAMOS A USAR
        String nombreFichero = "suicide-death-rates.csv";
        
        BufferedReader br = null;
        try {
            // Crear un objeto BufferedReader al que se le pasa 
            //   un objeto FileReader con el nombre del fichero
            br = new BufferedReader(new FileReader(nombreFichero));
            // Leer la primera línea, guardando en un String
           
            // OMITIMOS LA PRIMERA LÍNEA
            br.readLine();
            
            String texto = br.readLine();
            
            // Repetir mientras no se llegue al final del fichero
            while(texto != null) {
                // DETECTAMOS LA SEPARACION POR ","
                String[] valores = texto.split(",");
                
                // CREAMOS VARIABLES PARA ALMACENAR ESOS VALORES
                String pais = valores[0];
                String codigo = valores[1];
                String codigoPais="";
                String year = valores[2];
                String muertes = valores[3];
                
                // PARA OMITIR LAS REGIONES Y CONTINENTES COMPROBAMOS SI CODIGO ESTÁ 
                // VACÍO PARA SALTAR ESA LÍNEA, EN CASO DE QUE TENGA SE ALMACENARÁ
                // EN LA VARIABLE CODIGOPAIS
                if(codigo.isEmpty()){
                  texto = br.readLine();
                  continue;
                } else{
                    codigoPais = codigo;
                }
                
                // PASAMOS LOS VALORES DE STRING A OTROS FORMATOS QUE SEAN MÁS CORRECTOS
                // PARA CADA TIPO DE DATO
                RateSuicidio suicidio1 = new RateSuicidio();
                suicidio1.setPais(pais);
                suicidio1.setCodigo(codigoPais);
                suicidio1.setYear(Integer.parseInt(year));
                suicidio1.setMuertes(Double.valueOf(muertes));
                        
                rateSuicidios1.getListaRateSuicidios().add(suicidio1);
                // LO AÑADIMOS A LA LISTA DE PAISES
                listaPaises.add(pais);
                texto = br.readLine();
            }
            
            // https://www.iteramos.com/pregunta/4197/Como-puedo-eliminar-los-elementos-repetidos-de-ArrayList#:~:text=La%20forma%20m%C3%A1s%20sencilla%20de,hs%20%3D%20new%20HashSet()%3B%20hs.
            // PARA NO REPETIR LOS PAISES
            Set<String> hashSet = new HashSet<String>(listaPaises);
            
            listaPaises.clear();
            listaPaises.addAll(hashSet);
            
            // https://www.delftstack.com/es/howto/java/sort-a-list-in-java/
            // PARA ORDENAR LA LISTA DE PAISES INSERTADOS
            Collections.sort(listaPaises);
            
            ElementosPantalla elementos = new ElementosPantalla(listaPaises);
            
            this.getChildren().add(elementos);
        }
        // Captura de excepción por fichero no encontrado
        catch (FileNotFoundException ex) {
            System.out.println("Error: Fichero no encontrado");
            ex.printStackTrace();
        }
        // Captura de cualquier otra excepción
        catch(Exception ex) {
            System.out.println("Error de lectura del fichero");
            ex.printStackTrace();
        }
        // Asegurar el cierre del fichero en cualquier caso
        finally {
            try {
                // Cerrar el fichero si se ha podido abrir
                if(br != null) {
                    br.close();
                }
            }
            catch (Exception ex) {
                System.out.println("Error al cerrar el fichero");
                ex.printStackTrace();
            }
        }
    }
    
    public static ArrayList ObtenerDatos(ComboBox comboBoxPaisSeleccionado, ComboBox comboBoxOperacion){
        
        try {
           // ALMACENAMOS LOS VALORES DE LOS COMBOBOX
           String operacion = (String) comboBoxOperacion.getValue();
           String paisSeleccionado = (String) comboBoxPaisSeleccionado.getValue();
           
           for (int i=0;i<rateSuicidios1.getListaRateSuicidios().size();i++) {
               
                // COMPROBAMOS EN LA LISTA SI COINCIDE CON EL PAIS SELECCIONADO
                if(rateSuicidios1.getListaRateSuicidios().get(i).getPais().equals(paisSeleccionado)){
                    
                    // ALMACENAMOS LOS PORCENTAJES DE ESE PAIS
                    String porcentajeSuicidios = Double.toString(rateSuicidios1.getListaRateSuicidios().get(i).getMuertes());
                    
                    // Y LO AÑADIMOS A UN ARRAY
                    arrayPorcentaje.add(porcentajeSuicidios);

                    // AÑADIMOS EN UNA VARIABLE LOS AÑOS
                    int annos = rateSuicidios1.getListaRateSuicidios().get(i).getYear();
                    
                    // Y LO INSERTAMOS EN OTRO ARRAY
                    arrayAnnos.add(annos);
                }
            }
           
            Operaciones operaciones = new Operaciones(operacion, paisSeleccionado, arrayAnnos, arrayPorcentaje);
            // LIMPIAMOS LOS ARRAYS CREADOS ANTERIORMENTE
            arrayAnnos.clear();
            arrayPorcentaje.clear();
        }
        catch(Exception ex) {
            System.out.println("Error, no se ha detectado un país y una operación");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Información sobre el uso del programa");
            alert.setHeaderText("Debe completar la información para realizar la operación");
            alert.setContentText("Debe seleccionar un país y una operación para recibir la información");

            alert.showAndWait();
            ex.printStackTrace();
           
        }
        return arrayPorcentaje;
    }
}
