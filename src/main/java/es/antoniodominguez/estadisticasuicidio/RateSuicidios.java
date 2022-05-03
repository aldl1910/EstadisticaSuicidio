package es.antoniodominguez.estadisticasuicidio;

import java.util.ArrayList;

public class RateSuicidios {
    public RateSuicidios(){
        
    }
    private ArrayList<RateSuicidio> listaRateSuicidios  = new ArrayList();
   
    public ArrayList<RateSuicidio> getListaRateSuicidios(){
        return listaRateSuicidios;
        
    }

    public void setListaRateSuicidios(ArrayList<RateSuicidio> listaRateSuicidios){
        this.listaRateSuicidios = listaRateSuicidios;

    }
}
