/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import data.Paciente;
import java.util.ArrayList;

/**
 * Clase que permite modelar los datos de los empleados
 * @author Diego Ricardo Montoya Baez, Gamaliel Omar Marquez Villaseñor, Hernández Vásquez Fatima Montserrat
 * @version 1.1
 */
public class PacienteModelado {

    /**
     * Metodo que filtra la lista de pacientes por un parametro determinado
     * @param keyword parametro por el que se desea filtar
     * @param lista lista que se desea filtrar
     * @return un arreglo solo de los elemntos filtrados 
     */
    public static ArrayList<Paciente> filtrarListaPor(String keyword, ArrayList<Paciente> lista) {
        ArrayList<Paciente> listaFilatrada = new ArrayList();
        keyword = keyword.toLowerCase();
        for (Paciente paciente : lista) {
            if (paciente.getNombre().toLowerCase().contains(keyword)
                    || ("" + paciente.getEdad()).contains(keyword)) {
                listaFilatrada.add(paciente);
            }
        }
        return listaFilatrada;
    }
    /**
     * Metodo que ordena la lista de pacientes por un parametro determinado
     * @param param parametro por el que se desea ordenar la lista
     * @param lista lista a ordenar
     * @return lista ordenada
     */

    public static ArrayList<Paciente> odernarListaPor(String param, ArrayList<Paciente> lista) {
        ArrayList<Paciente> listaOrdenada;
        switch (param) {
            case "CLAVE":
                listaOrdenada = ordenarPorEdad(lista);
                break;
            default:
                listaOrdenada = ordenarPorNombre(lista);
                break;
        }
        return listaOrdenada;
    }
    /**
     * Metodo que ordena a los pacientes por nombre
     * @param lista lista a ordenar 
     * @return lista ordenada por nombre
     */
    

    private static ArrayList<Paciente> ordenarPorNombre(ArrayList<Paciente> lista) {
        ArrayList<Paciente> listaOrdenada = lista;
        int size = listaOrdenada.size();
        for (int j = 1; j < size; j++) {
            Paciente current = listaOrdenada.get(j);
            String currentstr = current.getNombre();
            int i = j - 1;
            while ((i > -1) && (listaOrdenada.get(i).getNombre().compareToIgnoreCase(currentstr) > 0)) {
                listaOrdenada.set(i + 1, listaOrdenada.get(i));
                i--;
            }
            listaOrdenada.set(i + 1, current);
        }
        return listaOrdenada;
    }
    /**
     * Metodo que ordena a los pacientes por edad
     * @param lista lista a ordenar 
     * @return lista ordenada por edad
     */

    private static ArrayList<Paciente> ordenarPorEdad(ArrayList<Paciente> lista) {

        int size = lista.size();
        for (int j = 1; j < size; j++) {
            Paciente current = lista.get(j);
            int currentInt = (int) current.getEdad();
            int i = j - 1;
            while ((i > -1) && (lista.get(i).getEdad() > currentInt)) {
                lista.set(i + 1, lista.get(i));
                i--;
            }
            lista.set(i + 1, current);
        }
        return lista;
    }
    /**
     * Metodo que genera una ficha con los datos del empleado
     * @param paciente datos del empleado a modelar
     * @return ficha del empleado
     */
    
    public static String generarFichaPaciente(Paciente paciente) {
        
        String ficha ="<span class='elemento-lista' onclick=\"window.location.href = 'http://localhost:8080/HospitalHMM/detalles-paciente.jsp?pacienteNSS="
                                    + paciente.getPacienteNSS()
                                    +"';\">"
                                    + "<img src='./imagenes/paciente50.png' alt=''>"
                                    + "<ul>"
                                    + "<li>"
                            +"<h6>" + paciente.getNombre() + "</h6>"
                                    + "</li>"
                            +"<li><p>Edad: " + paciente.getEdad() + "</p></li>"
                            +"</ul>"
                                    + "</span>";
        return ficha;
    }
}
