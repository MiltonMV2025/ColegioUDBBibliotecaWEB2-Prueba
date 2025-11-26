package sv.edu.udb.config;

import sv.edu.udb.models.Usuario;

public class SesionUsuario {

    private static SesionUsuario instancia;
    private Usuario usuario;

    private SesionUsuario() {}

    public static SesionUsuario getInstancia() {
        if (instancia == null) {
            instancia = new SesionUsuario();
        }
        return instancia;
    }

    public void iniciarSesion(Usuario usuario) {
        this.usuario = usuario;
    }

    public void cerrarSesion() {
        this.usuario = null;
    }

    public boolean haySesionActiva() {
        return usuario != null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public boolean esAdministrador() {
        return usuario != null && usuario.getNombreRol().equalsIgnoreCase("Administrador");
    }

    public boolean tieneAccesoAlModulo(String modulo) {
        if (usuario == null) return false;
        // puedes extender este método si quieres reglas de acceso por rol
        return true;
    }
}
