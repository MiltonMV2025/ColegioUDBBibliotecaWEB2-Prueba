package sv.edu.udb.models;

import java.sql.Date;

public class Prestamo {
    private int idPrestamo;
    private int idUsuario;
    private int idEjemplar;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String estado;
    
    private String nombreUsuario;
    private String codigoEjemplar;
    private String tituloDocumento;
    
    public Prestamo() {
    }
    
    public Prestamo(int idPrestamo, int idUsuario, int idEjemplar, Date fechaPrestamo,
                    Date fechaDevolucion, String estado) {
        this.idPrestamo = idPrestamo;
        this.idUsuario = idUsuario;
        this.idEjemplar = idEjemplar;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }
    
    public int getIdPrestamo() {
        return idPrestamo;
    }
    
    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public int getIdEjemplar() {
        return idEjemplar;
    }
    
    public void setIdEjemplar(int idEjemplar) {
        this.idEjemplar = idEjemplar;
    }
    
    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }
    
    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
    
    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }
    
    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getCodigoEjemplar() {
        return codigoEjemplar;
    }
    
    public void setCodigoEjemplar(String codigoEjemplar) {
        this.codigoEjemplar = codigoEjemplar;
    }
    
    public String getTituloDocumento() {
        return tituloDocumento;
    }
    
    public void setTituloDocumento(String tituloDocumento) {
        this.tituloDocumento = tituloDocumento;
    }
    
    @Override
    public String toString() {
        return "Prestamo{" +
                "idPrestamo=" + idPrestamo +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                ", estado='" + estado + '\'' +
                '}';
    }
}
