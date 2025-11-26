package sv.edu.udb.models;

import java.math.BigDecimal;
import java.sql.Date;

public class Devolucion {
    private int idDevolucion;
    private int idPrestamo;
    private Date fechaDevolucion;
    private String observaciones;
    private int diasAtraso;
    private BigDecimal moraGenerada;
    private BigDecimal moraPagada;
    
    public Devolucion() {
    }
    
    public Devolucion(int idDevolucion, int idPrestamo, Date fechaDevolucion, String observaciones) {
        this.idDevolucion = idDevolucion;
        this.idPrestamo = idPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.observaciones = observaciones;
    }
    
    public int getIdDevolucion() {
        return idDevolucion;
    }
    
    public void setIdDevolucion(int idDevolucion) {
        this.idDevolucion = idDevolucion;
    }
    
    public int getIdPrestamo() {
        return idPrestamo;
    }
    
    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }
    
    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }
    
    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public int getDiasAtraso() {
        return diasAtraso;
    }

    public void setDiasAtraso(int diasAtraso) {
        this.diasAtraso = diasAtraso;
    }

    public BigDecimal getMoraGenerada() {
        return moraGenerada;
    }

    public void setMoraGenerada(BigDecimal moraGenerada) {
        this.moraGenerada = moraGenerada;
    }

    public BigDecimal getMoraPagada() {
        return moraPagada;
    }

    public void setMoraPagada(BigDecimal moraPagada) {
        this.moraPagada = moraPagada;
    }
    
    @Override
    public String toString() {
        return "Devolucion{" +
                "idDevolucion=" + idDevolucion +
                ", idPrestamo=" + idPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                ", observaciones='" + observaciones + '\'' +
                ", diasAtraso=" + diasAtraso +
                ", moraGenerada=" + moraGenerada +
                ", moraPagada=" + moraPagada +
                '}';
    }
}
