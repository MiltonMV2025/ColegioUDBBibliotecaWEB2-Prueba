package sv.edu.udb.models;

public class Ejemplar {
    private int idEjemplar;
    private int idDocumento;
    private String codigoEjemplar;
    private String estado;
    
    private String tituloDocumento;
    
    public Ejemplar() {
    }
    
    public Ejemplar(int idEjemplar, int idDocumento, String codigoEjemplar, String estado) {
        this.idEjemplar = idEjemplar;
        this.idDocumento = idDocumento;
        this.codigoEjemplar = codigoEjemplar;
        this.estado = estado;
    }
    
    public int getIdEjemplar() {
        return idEjemplar;
    }
    
    public void setIdEjemplar(int idEjemplar) {
        this.idEjemplar = idEjemplar;
    }
    
    public int getIdDocumento() {
        return idDocumento;
    }
    
    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }
    
    public String getCodigoEjemplar() {
        return codigoEjemplar;
    }
    
    public void setCodigoEjemplar(String codigoEjemplar) {
        this.codigoEjemplar = codigoEjemplar;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getTituloDocumento() {
        return tituloDocumento;
    }
    
    public void setTituloDocumento(String tituloDocumento) {
        this.tituloDocumento = tituloDocumento;
    }
    
    @Override
    public String toString() {
        return "Ejemplar{" +
                "idEjemplar=" + idEjemplar +
                ", codigoEjemplar='" + codigoEjemplar + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
