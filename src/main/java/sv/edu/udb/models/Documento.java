package sv.edu.udb.models;

import java.sql.Timestamp;

public class Documento {
    private int idDocumento;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private int idCategoria;
    private String ubicacionFisica;
    private int cantidadTotal;
    private int cantidadDisponible;
    private Timestamp fechaRegistro;
    
    private String nombreCategoria;
    
    public Documento() {
    }
    
    public Documento(int idDocumento, String titulo, String autor, int anioPublicacion,
                     int idCategoria, String ubicacionFisica, int cantidadTotal,
                     int cantidadDisponible, Timestamp fechaRegistro) {
        this.idDocumento = idDocumento;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.idCategoria = idCategoria;
        this.ubicacionFisica = ubicacionFisica;
        this.cantidadTotal = cantidadTotal;
        this.cantidadDisponible = cantidadDisponible;
        this.fechaRegistro = fechaRegistro;
    }
    
    public int getIdDocumento() {
        return idDocumento;
    }
    
    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getAutor() {
        return autor;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public int getAnioPublicacion() {
        return anioPublicacion;
    }
    
    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
    
    public int getIdCategoria() {
        return idCategoria;
    }
    
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    public String getUbicacionFisica() {
        return ubicacionFisica;
    }
    
    public void setUbicacionFisica(String ubicacionFisica) {
        this.ubicacionFisica = ubicacionFisica;
    }
    
    public int getCantidadTotal() {
        return cantidadTotal;
    }
    
    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }
    
    public int getCantidadDisponible() {
        return cantidadDisponible;
    }
    
    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
    
    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }
    
    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    public String getNombreCategoria() {
        return nombreCategoria;
    }
    
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @Override
    public String toString() {
        return titulo;
    }

}
