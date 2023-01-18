/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import data.Empleado;
import java.util.ArrayList;

/**
 * Clase que permite modelar los datos de los empleados
 * @author Diego Ricardo Montoya Baez, Gamaliel Omar Marquez Villaseñor, Hernández Vásquez Fatima Montserrat
 * @version 1.1
 */
public class EmpleadoModelado {

    /**
     * Metodo que filtra la lista de empleados por un paramnetro determinado
     * @param keyword parametro por el que se desea filtar
     * @param lista lista que se desea filtrar
     * @return un arreglo solo de los elemntos filtrados 
     */
    public  ArrayList<Empleado> filtrarListaPor(String keyword, ArrayList<Empleado> lista) {
        ArrayList<Empleado> listaFilatrada = new ArrayList();
        if(lista == null) return listaFilatrada;
        if(keyword == null) return lista;
        keyword = keyword.toLowerCase();
        for (Empleado empleado : lista) {
            if (empleado.getNombre().toLowerCase().contains(keyword)
                    || ("" + empleado.getNumEmpleado()).contains(keyword)) {
                listaFilatrada.add(empleado);
            }
        }
        return listaFilatrada;
    }
    /**
     * Metodo que ordena la lista de empleados por un parametro determinado
     * @param param parametro por el que se desea ordenar la lista
     * @param lista lista a ordenar
     * @return lista ordenada
     */

    public  ArrayList<Empleado> ordenarListaPor(String param, ArrayList<Empleado> lista) {
        ArrayList<Empleado> listaOrdenada = new ArrayList<>();
        if(lista == null) return listaOrdenada;
        if(param == null) return lista;
        switch (param) {
            case "CLAVE":
                listaOrdenada = ordenarPorClave(lista);
                break;
            default:
                listaOrdenada = ordenarPorNombre(lista);
                break;
        }
        return listaOrdenada;
    }
    /**
     * Metodo que ordena a los empleados por nombre
     * @param lista lista a ordenar 
     * @return lista ordenada por nombre
     */

    private  ArrayList<Empleado> ordenarPorNombre(ArrayList<Empleado> lista) {
        ArrayList<Empleado> listaOrdenada = lista;
        int size = listaOrdenada.size();
        for (int j = 1; j < size; j++) {
            Empleado current = listaOrdenada.get(j);
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
     * Metodo que ordena a los empleados por clave 
     * @param lista lista a ordenar 
     * @return lista ordenada por clave
     */

    private  ArrayList<Empleado> ordenarPorClave(ArrayList<Empleado> lista) {

        int size = lista.size();
        for (int j = 1; j < size; j++) {
            Empleado current = lista.get(j);
            long currentLong = current.getNumEmpleado();
            int i = j - 1;
            while ((i > -1) && (lista.get(i).getNumEmpleado() > currentLong)) {
                lista.set(i + 1, lista.get(i));
                i--;
            }
            lista.set(i + 1, current);
        }
        return lista;
    }
    /**
     * Metodo que genera una ficha con los datos del empleado
     * @param empleado datos del empleado a modelar
     * @return ficha del empleado
     */

    public  String generarFichaEmpleado(Empleado empleado) {
        
        String ficha ="<span class='elemento-lista' onclick=\"window.location.href = 'http://localhost:8080/HospitalHMM/detalles-empleado.jsp?numEmpleado="
                                    + empleado.getNumEmpleado()
                                    +          "';\">"
                                    + "<img src='./imagenes/med50.png' alt=''>"
                                    + "<ul>"
                                    + "<li>"
                            +"<h6>" + empleado.getNombre() + "</h6>"
                                    + "</li>"
                            +"<li><p>NE." + empleado.getNumEmpleado() + "</p></li>"
                            +"</ul>"
                                    + "</span>";
        return ficha;
    }

}
