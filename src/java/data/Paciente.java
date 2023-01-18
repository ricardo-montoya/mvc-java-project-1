/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 * Clase que permite encapsular los datos de paciente obtenidos de la base de datos
 * @author Diego Ricardo Montoya Baez
 * @version 1.0
 */
public class Paciente {
    private String pacienteNSS;
    private String nombre;
    private double edad;
    private String diagnostico;

    public Paciente() {
    }
    /**
     * Constructor inicializado de la clase paciente 
     * @param pacienteNSS inicializa el valor del NSS
     * @param nombre inicializa el valor del nombre
     * @param edad inicializa el valor de la edad
     * @param diagnostico inicializa el valor del diagnostico
     */

    public Paciente(String pacienteNSS, String nombre, double edad, String diagnostico) {
        this.pacienteNSS = pacienteNSS;
        this.nombre = nombre;
        this.edad = edad;
        this.diagnostico = diagnostico;
    }
    /**
     * Metodo de acceso getter del atributo NSS
     * @return valor del NSS
     */

    public String getPacienteNSS() {
        return pacienteNSS;
    }
    /**
     * Metodo de acceso setter del atributo NSS
     * @param pacienteNSS establece el valor del NSS
     */

    public void setPacienteNSS(String pacienteNSS) {
        this.pacienteNSS = pacienteNSS;
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
     * Metodo de acceso getter del atributo edad
     * @return el valor de la edad
     */

    public double getEdad() {
        return edad;
    }
    /**
     * Metodo de acceso setter del atributo edad
     * @param edad establece el valor de la edad
     */

    public void setEdad(double edad) {
        this.edad = edad;
    }
    /**
     * Metodo de acceso getter del atributo diagnostico
     * @return el valor del diagnostico
     */

    public String getDiagnostico() {
        return diagnostico;
    }
    /**
     * Metodo de acceso setter del atributo diagnostico
     * @param diagnostico establece el valor del diagnostico
     */

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    @Override
    public String toString() {
       return "PacienteNSS: "  + pacienteNSS + ", Nombre: " + nombre + ", Edad: " + edad + ", Diagnostico: " + diagnostico;
    }
    
}
