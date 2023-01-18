/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.PacienteDAO;
import data.Paciente;
import java.util.ArrayList;
import model.PacienteEstadisticas;

/**
 *
 * @author Diego R
 */
public class ControllerPaciente {
    public ArrayList<Paciente> obtenerPacientes() {
        return new PacienteDAO().obtenerPacientes();
    }
    public ArrayList<Paciente> obtenerPacientes(String where) {
        return new PacienteDAO().obtenerPacientes(where);
        
    }
    
    public Paciente obtenerPaciente(String pacienteNSS) {
        return new PacienteDAO().obtenerPaciente(pacienteNSS);
    }
    
    public boolean insertarPaciente(Paciente paciente) {
        return new PacienteDAO().insertarPaciente(paciente);
    }
    
    public boolean actualizarPaciente(String pacienteNSS, Paciente pacienteModificado) {
        return new PacienteDAO().actualizarPaciente(pacienteNSS, pacienteModificado);
    }
    
    public boolean eliminarPaciente(String pacienteNSS) {
        return new PacienteDAO().eliminarPaciente(pacienteNSS);
    }

     public int obtenerNumeroTotalDePacientes(){
        return new PacienteEstadisticas().obtenerNumeroTotalDePacientes();
    }
     
    public double obtenerEdadPromedioDePacientes() {
        return new PacienteEstadisticas().obtenerEdadPromedioDePacientes();
    }
    
    public Paciente obtenerPacienteConMayorAntiguedad() {
        return new PacienteEstadisticas().obtenerPacienteConMayorAntiguedad();
    }
    
    public Paciente obtenerPacienteConMenorAntiguedad() {
        return new PacienteEstadisticas().obtenerPacienteConMenorAntiguedad();
    }
    
    public ArrayList<Paciente> obtenerPacientesDiagnosticoVocal() {
        return new PacienteEstadisticas().obtenerPacientesDiagnosticoVocal();
    }

}
