package data;

/**
 * Clase que permite encapsular los datos de medicamento obtenidos de la base de datos
 * @author Diego Ricardo Montoya Baez
 * @version 1.0
 */
public class Medicamento {
    private String clave;
    private String nombre;
    private String sustancia;
    private String presentacion;
    private String administracion;

    /**
     * Constructor inicializado de la calse medicamento
     * @param clave inicializa el valor de la clave
     * @param nombre inicializa el valor del nombre
     * @param sustancia iniciliza el valor de la sustancia
     * @param presentacion iniciliza el valor de la  presentacion
     * @param administracion iniciliza el valor de la via de administracion
     */
    public Medicamento(String clave, String nombre, String sustancia, String presentacion, String administracion) {
        this.clave = clave;
        this.nombre = nombre;
        this.sustancia = sustancia;
        this.presentacion = presentacion;
        this.administracion = administracion;
    }

    public Medicamento() {
    }
    /**
     * Metodo de acceso getter del atributo clave
     * @return el valor de la clave
     */
    public String getClave() {
        return clave;
    }
    /**
     * Metodo de acceso setter del atributo clave
     * @param clave establece el valor de la clave
     */

    public void setClave(String clave) {
        this.clave = clave;
    }
    /**
     * Metodo de acceso getter del atributo nombre
     * @return el valor del nombre
     */

    public String getNombre() {
        return nombre;
    }
    /**
     * Metodo de acceso setter del atributo nombre
     * @param nombre establece el valor del nombre
     */

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Metodo de acceso getter del atributo sustancia
     * @return el valor de la sustancia
     */

    public String getSustancia() {
        return sustancia;
    }
    /**
     * Metodo de acceso setter del atributo sustancia
     * @param sustancia establece el valor de la sustancia
     */

    public void setSustancia(String sustancia) {
        this.sustancia = sustancia;
    }
    /**
     * Metodo de acceso getter del atributo presentacion
     * @return el valor de la presentacion
     */

    public String getPresentacion() {
        return presentacion;
    }
    /**
     * Metodo de acceso setter del atributo presentacion
     * @param presentacion establece el valor de la presentacion
     */

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }
    /**
     * Metodo de acceso getter del atributo via de administracion
     * @return el valor de la via de administracion
     */

    public String getAdministracion() {
        return administracion;
    }
    /**
     * Metodo de acceso setter del atributo via de administracion
     * @param administracion establece la via de administracion
     */

    public void setAdministracion(String administracion) {
        this.administracion = administracion;
    }

    

    
    
    
    
}
