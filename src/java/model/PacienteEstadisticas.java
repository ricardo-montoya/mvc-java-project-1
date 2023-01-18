/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DAO.PacienteDAO;
import data.Paciente;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que permite calcular las estadisticas de los pacientes
 * @author Diego Ricardo Montoya Baez, Gamaliel Omar Marquez Villaseñor, Hernández Vásquez Fatima Montserrat
 * @version 1.0
 */
public class PacienteEstadisticas {

    /**
     *Metodo que calcula el numero total de pacientes 
     * @return el numero total de pacientes
     */
    public int obtenerNumeroTotalDePacientes() {
        return new PacienteDAO().obtenerPacientes().size();
    }

    /**
     *Metodo que calcula la edad promedio de los pacientes
     * @return la edad promedio de los pacientes 
     */
    public double obtenerEdadPromedioDePacientes() {
        ArrayList<Paciente> pacientes = new PacienteDAO().obtenerPacientes();
        double edadPromedio;
        double sumaDeEdades = 0;
        for (Paciente paciente : pacientes) {
            sumaDeEdades += paciente.getEdad();
        }
        edadPromedio = sumaDeEdades / obtenerNumeroTotalDePacientes();
        return edadPromedio;
    }
    
    /**
     * Metodo que obtiene la cantidad de pacientes que su diagnostico comienza con ua vocal
     * @return la cantidad de pacientes que si diagnostico comienza con una vocal
     */
    public ArrayList<Paciente> obtenerPacientesDiagnosticoVocal() {
        ArrayList<Paciente> pacientesRecibidos = new PacienteDAO().obtenerPacientes();
        ArrayList<Paciente> pacientes = new ArrayList();
        
        String vocales = "aeiou";
        for (Paciente paciente : pacientesRecibidos) {
            int compararCharAtVocal = 0;
            while (compararCharAtVocal != 5) {
                if (paciente.getDiagnostico().toLowerCase().charAt(0) == vocales.charAt(compararCharAtVocal)) {
                    pacientes.add(paciente);
                }
                compararCharAtVocal++;
            }
        }
        return pacientes;
    }
    
    /**
     * Metodo que calcula el paciente con la mayor antiguedad
     * @return datos del paciente con mayor antiguedad
     */
     public Paciente obtenerPacienteConMayorAntiguedad() {
        ArrayList<Paciente> pacientes = new PacienteDAO().obtenerPacientes();
        ArrayList<Integer> antiguedades = new ArrayList();   
        for (Paciente paciente : pacientes) {
            String antiguedadPacienteActual = paciente.getPacienteNSS().substring(2,4);
            int añoDeAfiliacionPacienteActual = Integer.parseInt(antiguedadPacienteActual);
            if(añoDeAfiliacionPacienteActual > 23) {
                añoDeAfiliacionPacienteActual += 1900;
            } else {
                añoDeAfiliacionPacienteActual += 2000;
            }
            antiguedades.add(2023 - añoDeAfiliacionPacienteActual);
        }
        int mayorAntiguedad  = Collections.max(antiguedades);
        int indiceConMayorAntiguedad = antiguedades.indexOf(mayorAntiguedad);
        return pacientes.get(indiceConMayorAntiguedad);
    }
    
     /**
      * Metodo que calcula al paciente con menor antiguedad
      * @return datos del paciente con menor antiguedad
      */
     public Paciente obtenerPacienteConMenorAntiguedad() {
        ArrayList<Paciente> pacientes = new PacienteDAO().obtenerPacientes();
        ArrayList<Integer> antiguedades = new ArrayList();   
        for (Paciente paciente : pacientes) {
            String antiguedadPacienteActual = paciente.getPacienteNSS().substring(2,4);
            int añoDeAfiliacionPacienteActual = Integer.parseInt(antiguedadPacienteActual);
            if(añoDeAfiliacionPacienteActual > 23) {
                añoDeAfiliacionPacienteActual += 1900;
            } else {
                añoDeAfiliacionPacienteActual += 2000;
            }
            antiguedades.add(2023 - añoDeAfiliacionPacienteActual);
        }
        int mayorAntiguedad  = Collections.min(antiguedades);
        int indiceConMayorAntiguedad = antiguedades.indexOf(mayorAntiguedad);
        return pacientes.get(indiceConMayorAntiguedad);
    }

}
