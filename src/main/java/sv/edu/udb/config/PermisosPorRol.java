package sv.edu.udb.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PermisosPorRol {
    
    public static final String MODULO_USUARIOS = "USUARIOS";
    public static final String MODULO_DOCUMENTOS = "DOCUMENTOS";
    public static final String MODULO_CATEGORIAS = "CATEGORIAS";
    public static final String MODULO_PRESTAMOS = "PRESTAMOS";
    public static final String MODULO_DEVOLUCIONES = "DEVOLUCIONES";
    public static final String MODULO_REPORTES = "REPORTES";
    public static final String MODULO_CONFIGURACION = "CONFIGURACION";
    
    public static final String ACCION_CREAR = "CREAR";
    public static final String ACCION_LEER = "LEER";
    public static final String ACCION_ACTUALIZAR = "ACTUALIZAR";
    public static final String ACCION_ELIMINAR = "ELIMINAR";
    public static final String ACCION_EXPORTAR = "EXPORTAR";
    
    private static final Map<String, Set<String>> permisosPorRol = new HashMap<>();
    
    static {
        inicializarPermisos();
    }

    private static void inicializarPermisos() {

        Set<String> permisosAdmin = new HashSet<>();
        
        permisosAdmin.add(permiso(MODULO_USUARIOS, ACCION_CREAR));
        permisosAdmin.add(permiso(MODULO_USUARIOS, ACCION_LEER));
        permisosAdmin.add(permiso(MODULO_USUARIOS, ACCION_ACTUALIZAR));
        permisosAdmin.add(permiso(MODULO_USUARIOS, ACCION_ELIMINAR));
        
        permisosAdmin.add(permiso(MODULO_DOCUMENTOS, ACCION_CREAR));
        permisosAdmin.add(permiso(MODULO_DOCUMENTOS, ACCION_LEER));
        permisosAdmin.add(permiso(MODULO_DOCUMENTOS, ACCION_ACTUALIZAR));
        permisosAdmin.add(permiso(MODULO_DOCUMENTOS, ACCION_ELIMINAR));
        
        permisosAdmin.add(permiso(MODULO_CATEGORIAS, ACCION_CREAR));
        permisosAdmin.add(permiso(MODULO_CATEGORIAS, ACCION_LEER));
        permisosAdmin.add(permiso(MODULO_CATEGORIAS, ACCION_ACTUALIZAR));
        permisosAdmin.add(permiso(MODULO_CATEGORIAS, ACCION_ELIMINAR));
        
        permisosAdmin.add(permiso(MODULO_PRESTAMOS, ACCION_CREAR));
        permisosAdmin.add(permiso(MODULO_PRESTAMOS, ACCION_LEER));
        permisosAdmin.add(permiso(MODULO_PRESTAMOS, ACCION_ACTUALIZAR));
        permisosAdmin.add(permiso(MODULO_PRESTAMOS, ACCION_ELIMINAR));
        
        permisosAdmin.add(permiso(MODULO_DEVOLUCIONES, ACCION_CREAR));
        permisosAdmin.add(permiso(MODULO_DEVOLUCIONES, ACCION_LEER));
        permisosAdmin.add(permiso(MODULO_DEVOLUCIONES, ACCION_ACTUALIZAR));
        permisosAdmin.add(permiso(MODULO_DEVOLUCIONES, ACCION_ELIMINAR));
        
        permisosAdmin.add(permiso(MODULO_REPORTES, ACCION_LEER));
        permisosAdmin.add(permiso(MODULO_REPORTES, ACCION_EXPORTAR));
        
        permisosAdmin.add(permiso(MODULO_CONFIGURACION, ACCION_LEER));
        permisosAdmin.add(permiso(MODULO_CONFIGURACION, ACCION_ACTUALIZAR));
        
        permisosPorRol.put("Administrador", permisosAdmin);


        Set<String> permisosEstudiante = new HashSet<>();
        
        permisosEstudiante.add(permiso(MODULO_USUARIOS, ACCION_LEER));
        
        permisosEstudiante.add(permiso(MODULO_DOCUMENTOS, ACCION_LEER));
        
        permisosEstudiante.add(permiso(MODULO_CATEGORIAS, ACCION_LEER));
        
        permisosEstudiante.add(permiso(MODULO_PRESTAMOS, ACCION_CREAR));
        permisosEstudiante.add(permiso(MODULO_PRESTAMOS, ACCION_LEER));
        permisosEstudiante.add(permiso(MODULO_PRESTAMOS, ACCION_ACTUALIZAR));
        
        permisosEstudiante.add(permiso(MODULO_DEVOLUCIONES, ACCION_CREAR));
        permisosEstudiante.add(permiso(MODULO_DEVOLUCIONES, ACCION_LEER));
        
        permisosEstudiante.add(permiso(MODULO_REPORTES, ACCION_LEER));
        
        permisosPorRol.put("Estudiante", permisosEstudiante);
        
        
        Set<String> permisosUsuario = new HashSet<>();
        
        permisosUsuario.add(permiso(MODULO_DOCUMENTOS, ACCION_LEER));
        
        permisosUsuario.add(permiso(MODULO_CATEGORIAS, ACCION_LEER));
        
        permisosUsuario.add(permiso(MODULO_PRESTAMOS, ACCION_LEER));
        
        permisosUsuario.add(permiso(MODULO_DEVOLUCIONES, ACCION_LEER));
        
        permisosPorRol.put("Usuario", permisosUsuario);
    }
    

    private static String permiso(String modulo, String accion) {
        return modulo + ":" + accion;
    }
    

    public static boolean tienePermiso(String nombreRol, String modulo, String accion) {
        if (nombreRol == null || modulo == null || accion == null) {
            return false;
        }
        
        Set<String> permisos = permisosPorRol.get(nombreRol);
        if (permisos == null) {
            return false;
        }
        
        return permisos.contains(permiso(modulo, accion));
    }
    

    public static boolean tieneAccesoAlModulo(String nombreRol, String modulo) {
        if (nombreRol == null || modulo == null) {
            return false;
        }
        
        Set<String> permisos = permisosPorRol.get(nombreRol);
        if (permisos == null) {
            return false;
        }
        
        for (String permiso : permisos) {
            if (permiso.startsWith(modulo + ":")) {
                return true;
            }
        }
        
        return false;
    }
    

    public static Set<String> obtenerPermisos(String nombreRol) {
        Set<String> permisos = permisosPorRol.get(nombreRol);
        return permisos != null ? new HashSet<>(permisos) : new HashSet<>();
    }

    public static boolean tieneCRUDCompleto(String nombreRol, String modulo) {
        return tienePermiso(nombreRol, modulo, ACCION_CREAR) &&
               tienePermiso(nombreRol, modulo, ACCION_LEER) &&
               tienePermiso(nombreRol, modulo, ACCION_ACTUALIZAR) &&
               tienePermiso(nombreRol, modulo, ACCION_ELIMINAR);
    }
}
