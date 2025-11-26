package sv.edu.udb.models;

import java.sql.Timestamp;

public class Historial {
    private int idHistorial;
    private int idUsuario;
    private String accion;
    private Timestamp fecha;
    
    private String nombreUsuario;
    
    public Historial() {
    }
    
    public Historial(int idHistorial, int idUsuario, String accion, Timestamp fecha) {
        this.idHistorial = idHistorial;
        this.idUsuario = idUsuario;
        this.accion = accion;
        this.fecha = fecha;
    }
    
    public int getIdHistorial() {
        return idHistorial;
    }
    
    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getAccion() {
        return accion;
    }
    
    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public Timestamp getFecha() {
        return fecha;
    }
    
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    @Override
    public String toString() {
        return "Historial{" +
                "idHistorial=" + idHistorial +
                ", accion='" + accion + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
