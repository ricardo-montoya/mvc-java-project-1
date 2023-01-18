/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.EmpleadoDAO;
import data.Empleado;
import data.Sexo;
import java.util.ArrayList;
import model.EmpleadoEstadisticas;
import model.EmpleadoModelado;

/**
 *
 * @author Diego R
 */
public class ControllerEmpleado {
     public ArrayList<Empleado> obtenerEmpleados() {
        return new EmpleadoDAO().obtenerEmpleados();
    }
    public ArrayList<Empleado> obtenerEmpleados(String where) {
        return new EmpleadoDAO().obtenerEmpleados(where);
        
    }
    
    public Empleado obtenerEmpleado(String numEmpleado) {
        return new EmpleadoDAO().obtenerEmpleado(numEmpleado);
    }
    
    public boolean insertarEmpleado(Empleado empleado) {
        return new EmpleadoDAO().insertarEmpleado(empleado);
    }
    
    public boolean actualizarEmpleado(long numEmpleado, Empleado empleadoModificado) {
        return new EmpleadoDAO().actualizarEmpleado(numEmpleado, empleadoModificado);
    }
    public boolean eliminarEmpleado(long numEmpleado) {
        return new EmpleadoDAO().eliminarEmpleado(numEmpleado);
    }
    public Sexo mapSexo(String cadena) {
        return new EmpleadoDAO().mapSexo(cadena);
    }
    
    public Empleado obtenerEmpleadoConMayorSueldo() {
        return new EmpleadoEstadisticas().obtenerEmpleadoConMayorSueldo();
    }
    public Empleado obtenerEmpleadoConMenorSueldo() {
        return new EmpleadoEstadisticas().obtenerEmpleadoConMenorSueldo();
    }

    public double obtenerSalarioPromedio() {
        return new EmpleadoEstadisticas().obtenerSalarioPromedio();
    }
    public int obtenerNumeroDeEmpleadosFemeninos(){
        return new EmpleadoEstadisticas().obtenerNumeroDeEmpleadosFemeninos();
    }
    public int obtenerNumeroDeEmpleadosOtro(){
        return new EmpleadoEstadisticas().obtenerNumeroDeEmpleadosOtro();
    }
    public int obtenerNumeroTotalEmpleados(){
        return new EmpleadoEstadisticas().obtenerNumeroTotalEmpleados();
    }
    public int obtenerNumeroDeEmpleadosMasculinos(){
        return new EmpleadoEstadisticas().obtenerNumeroDeEmpleadosMasculinos();
    } 
    public ArrayList<Empleado> odernarListaPor(String param, ArrayList<Empleado> lista){
        return new EmpleadoModelado().ordenarListaPor(param, lista);
    }
    public  ArrayList<Empleado> filtrarListaPor(String keyword, ArrayList<Empleado> lista){
        return new EmpleadoModelado().filtrarListaPor(keyword, lista);
    }
    public  String generarFichaEmpleado(Empleado empleado){
        return new EmpleadoModelado().generarFichaEmpleado(empleado);
    }
    
}
