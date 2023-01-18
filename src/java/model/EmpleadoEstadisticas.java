/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DAO.EmpleadoDAO;
import data.Empleado;
import java.util.ArrayList;

/**
 * Clase que permite determinar las estadisticas de los empleados
 * @author Diego Ricardo Montoya Baez, Gamaliel Omar Marquez Villaseñor, Hernández Vásquez Fatima Montserrat
 * @version 1.1
 */
public class EmpleadoEstadisticas {

    /*
        Mayor sueldo
    Menor sueldo
    SueldoPromedio
    NumeroDeEmpelados
    Num de Empleados Masc
    NumDe Empleados Fem
     */

    /**
     * Metodo que calcula al empleado con moyor sueldo
     * @return los datos del empleado con mayor sueldo
     */
    public Empleado obtenerEmpleadoConMayorSueldo() {
        ArrayList<Empleado> list = new EmpleadoDAO().obtenerEmpleados();
        return list.get(0);
    }
    /**
     * Metodo que calcula al empleado con menor sueldo
     * @return los datos del empleado con menor sueldo
     */

    public Empleado obtenerEmpleadoConMenorSueldo() {
        ArrayList<Empleado> list = new EmpleadoDAO().obtenerEmpleados();
        return list.get(list.size() - 1);
    }
    /**
     * Metodo que calcula el salario pomedio de los empleados
     * @return el salario promedio de todos los empleados 
     */

    public double obtenerSalarioPromedio() {
        ArrayList<Empleado> list = new EmpleadoDAO().obtenerEmpleados();
        double total = 0;
        for (Empleado empleado : list) {
            total += empleado.getSalario();
        }
        double salarioPromedio = total / (double) list.size();
        return salarioPromedio;
    }
    /**
     * Metodo que determina la cantidad de empleados de genero femenino
     * @return el numero de empleados de genero femenino
     */
    public int obtenerNumeroDeEmpleadosFemeninos(){
        ArrayList<Empleado> list = new EmpleadoDAO().obtenerEmpleados("sexo LIKE 'femenino'");
        return list.size();
    }
    /**
     * Metodo que calcula la cantidad de empleados de otros generos
     * @return el numero de empleados de otros generos
     */
    public int obtenerNumeroDeEmpleadosOtro(){
        ArrayList<Empleado> list = new EmpleadoDAO().obtenerEmpleados("(sexo LIKE 'otro')");
        return list.size();
    }
    /**
     * Metodo que calcula el numero total de empleados 
     * @return el numero total de empleados
     */
    public int obtenerNumeroTotalEmpleados(){
        return new EmpleadoDAO().obtenerEmpleados().size();
    }
    /**
     * Metodo que calcula la cantidad de empleados de genero masculono
     * @return el numero de empleados de genero masculino
     */
    public int obtenerNumeroDeEmpleadosMasculinos(){
        ArrayList<Empleado> list = new EmpleadoDAO().obtenerEmpleados("sexo LIKE 'masculino'");
        return list.size();
    }
    
}
