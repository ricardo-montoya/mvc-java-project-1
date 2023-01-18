/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 * Clase que permite encapsular los datos de empleado obtenidos de la base de datos
 * @author Diego Ricardo Montoya Baez
 * @version 1.0
 */
public class Empleado {
    private int edad;
    private String nombre;
    private long numEmpleado;
    private double salario;
    private Sexo sexo;

 
    public Empleado() {
        this.edad = 99;
        this.nombre = "empty";
        this.sexo = Sexo.Femenino;
        this.numEmpleado = 20000990;
                
    }

    /**
     * Contructor inicializado de la clase empleado
     * @param edad inicializa el valor de la edad 
     * @param nombre inicializa el valor del nombre
     * @param numEmpleado inicializa el valor del numero de empleado 
     * @param salario inicializa el valor del salario
     * @param sexo inicializa el valor de sexo
     */
    public Empleado(int edad, String nombre, long numEmpleado, double salario, Sexo sexo) {
        this.edad = edad;
        this.nombre = nombre;
        this.numEmpleado = numEmpleado;
        this.salario = salario;
        this.sexo = sexo;
    }

    /**
     * Metodo de acceso getter del atributo edad
     * @return el valor de la edad
     */
    public int getEdad() {
        return edad;
    }
/**
 * Metodo de acceso setter del atributo edad
 * @param edad establece el valor de la edada
 */
    public void setEdad(int edad) {
        this.edad = edad;
    }
/**
 * Metodo de acceso getter del atrubuto nombre
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
 * Metodo de acceso getter del atributo numero de empleado
 * @return el valor del numero de empleado
 */
    public long getNumEmpleado() {
        return numEmpleado;
    }
/**
 * Metodo de acceso setter del atributo numero de empleado
 * @param numEmpleado establece el valor del numero de empleado
 */
    public void setNumEmpleado(long numEmpleado) {
        this.numEmpleado = numEmpleado;
    }
/**
 * Metodo de acceso getter del atributo salario
 * @return el valor del salario
 */
    public double getSalario() {
        return salario;
    }
/**
 * Metodo de acceso setter del atributo salario
 * @param salario establece el valor del salario
 */
    public void setSalario(double salario) {
        this.salario = salario;
    }
/**
 * Metodo de acceso getter del atributo sexo
 * @return el tipo de sexo
 */
    public Sexo getSexo() {
        return sexo;
    }
/**
 * Metodo de acceso setter del atributo sexo
 * @param sexo establece el tipo de sexo
 */
    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
    /**
     * Metodo que valida que los datos no sean nulos
     * @return el estado de las datos 
     */
    public boolean validData(){
        boolean estado = false;
        if (this.numEmpleado >1 && this.nombre.length() > 10 && this.salario > 999 && this.sexo != null){
            estado = true;
        } 
        return estado;
    }
    
    /**
     * Metodo que sobreescribe los datos recibidos 
     * @return modelo establecido de los datos
     */
    @Override
    public String toString() {
        return "Empleado{" + "edad=" + edad + ", nombre=" + nombre + ", numEmpleado=" + numEmpleado + ", salario=" + salario + ", sexo=" + sexo + '}';
    }
    
}
