package es.antoniodominguez.estadisticasuicidio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Operaciones {
    // CLASE PARA METER LA MEDIA Y ESAS COSAS
            
    public Operaciones(String operacion, String paisSeleccionado, ArrayList arrayAnnos, ArrayList arrayPorcentaje){
        // ARRAY CREADAS CON EL CONTENIDO QUE SE LE PASA A LA CLASE POR PARÁMETROS
        ArrayList<String> listaSuicidios = arrayPorcentaje;
        ArrayList listaAnnos = arrayAnnos;
        
        // VARIABLE QUE ALMACENA EL TEXTO QUE APARECERÁ EN LA PANTALLA CON LOS DATOS 
        String textoResultado = "";
        
        // VARIABLES PARA ALMACENAR LOS DATOS DE LAS OPERACIONES
        double minimo = Double.parseDouble(listaSuicidios.get(0));
        double media = 0.0;
        double maximo = Double.parseDouble(listaSuicidios.get(0));
        
        // https://www.delftstack.com/es/howto/java/how-to-round-a-double-to-two-decimal-places-in-java/
        // VARIABLE PARA ALMACENAR EL REDONDEO
        double redondeo;
        
        // VARIABLE QUE ALMACENA EL AÑO
        int annoActual ;
        
        // ID CREADO PARA ALMACENAR EL ÍNDICE DE UN BUCLE FOR
        int id = 0;
        
        BufferedWriter bw = null;
        
        // FICHERO QUE SE CREARÁ AL REALIZAR LA OPERACIÓN CON EL CONTENIDO DE ESTA
        String nombreFichero = "porcentajeSuicidios.csv";
        
        // SWITCH QUE DETECTA EL TIPO DE OPERACIÓN QUE SE HA ESCOGIDO
        switch (operacion) {
            case "Mínimo":
                // BUCLE FOR QUE RECORRE LA LISTA DE SUICIDIOS POR SU TAMAÑO
                for (int i=0;i<listaSuicidios.size();i++) {
                    // SI EL VALOR ES MENOR QUE LA VARIABLE MINIMO, EL CONTENIDO
                    // DE MINIMO PASARÁ A SER EL DE LA LISTA. EL VALOR DE ID SE ACTUALIZA
                    // POR EL VALOR DEL ÍNDICE DEL TAMAÑO DE LA LISTA
                    if (Double.parseDouble(listaSuicidios.get(i)) < minimo ){
                        minimo=Double.parseDouble(listaSuicidios.get(i));
                        id=i;
                    }
                    // SE REALIZA EL REDONDEO
                    redondeo = Math.round(minimo*100.0)/100.0;
                    
                    // ACTUALIZAMOS EL CONTENIDO DE LA VARIABLE QUE ALMACENA EL TEXTO
                    textoResultado = "Porcentaje mínimo de suicidios: " +  redondeo + "%";
                }
                // ALMACENA EL AÑO POR EL ÍNDICE QUE TIENE EL VALOR DE ID
                annoActual= Integer.parseInt(listaAnnos.get(id).toString());
                
                // SE LE AÑADE EL AÑO AL TEXTO
                textoResultado += " año " + annoActual;
                
                System.out.println(textoResultado);
                break;
            
            case "Media":
                // BUCLE FOR QUE RECORRE LA LISTA DE SUICIDIOS POR SU TAMAÑO
                for (int i=0;i<listaSuicidios.size();i++) {
                    // SUMAMOS EL VALOR DE LA LISTA CON EL VALOR DE MEDIA
                    media = media + Double.parseDouble(listaSuicidios.get(i));
                }
                // DIVIDIMOS EL VALOR DE MEDIA ENTRE EL TAMAÑO DE LA LISTA
                media = media / listaSuicidios.size();
                
                // SE REALIZA EL REDONDEO
                redondeo = Math.round(media*100.0)/100.0;
                
                // MODIFICAMOS EL VALOR DE LA VARIABLE TEXTORESULTADO
                textoResultado = "Porcentaje medio de suicidios entre los años "+ listaAnnos.get(0) + " y " + listaAnnos.get(listaAnnos.size()-1) + " : " +  redondeo + "%";
                
                System.out.println(textoResultado);
                break;
                
            case "Máximo":
                // SI EL VALOR ES MAYOR QUE LA VARIABLE MÁXIMO, EL CONTENIDO
                // DE MÁXIMO PASARÁ A SER EL DE LA LISTA. EL VALOR DE ID SE ACTUALIZA
                // POR EL VALOR DEL ÍNDICE DEL TAMAÑO DE LA LISTA
                for (int i=0;i<listaSuicidios.size();i++) {
                    if (Double.parseDouble(listaSuicidios.get(i)) > maximo  ){
                        maximo = Double.parseDouble(listaSuicidios.get(i)); 
                        id=i;
                    }
                    // SE REALIZA EL REDONDEO
                    redondeo = Math.round(maximo*100.0)/100.0;
                    textoResultado = "Porcentaje máximo de suicidios: " +  redondeo + "%"; 
                }
                // ALMACENA EL AÑO POR EL ÍNDICE QUE TIENE EL VALOR DE ID
                annoActual= Integer.parseInt(listaAnnos.get(id).toString());
                
                // SE LE AÑADE EL AÑO AL TEXTO
                textoResultado += " año " + annoActual;
                
                System.out.println(textoResultado);
                break;
        }
        try {
            //Crear un objeto BufferedWriter. Si ya existe el fichero, 
            //  se borra automáticamente su contenido anterior.
            bw = new BufferedWriter(new FileWriter(nombreFichero));
            //Escribir en el fichero el texto con un salto de línea
            bw.write(paisSeleccionado + "\n" + textoResultado);
        }
        // Comprobar si se ha producido algún error
        catch(Exception ex) {
           System.out.println("Error de escritura del fichero");
           ex.printStackTrace();
        }
        // Asegurar el cierre del fichero en cualquier caso
        finally {
            try {
                // Cerrar el fichero si se ha podido abrir
                if(bw != null)
                    bw.close();
            }
            catch (Exception ex) {
                System.out.println("Error al cerrar el fichero");
                ex.printStackTrace();
            }
        }
        Texto.text.setText(textoResultado);
    }
}
