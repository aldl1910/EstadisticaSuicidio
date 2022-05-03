package es.antoniodominguez.estadisticasuicidio;

public class RateSuicidio {
    private String pais;
    private String codigo;
    private int year;
    private double muertes;

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getMuertes() {
        return muertes;
    }

    public void setMuertes(double muertes) {
        this.muertes = muertes;
    }  
    
    @Override
    public String toString(){
        String r = "";
        r += "Pais: " + pais + "\n";
        r += "Codigo: " + codigo + "\n";
        r += "Año: " + year + "\n";
        r += "Suicidios: " + muertes + "\n";
        return r;
    }
    
}
