/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.Medicamento;
import java.util.ArrayList;

/**
 * Clase que permite modelar los datos de los medicamentos
 * @author Diego Ricardo Montoya Baez, Gamaliel Omar Marquez Villaseñor, Hernández Vásquez Fatima Montserrat
 * @version 1.1
 */
public class MedicamentoModelado {

    /**
     * Metodo que filtra la lista de medicamentos por un paramnetro determinado
     * @param keyword parametro por el que se desea filtar
     * @param lista lista que se desea filtrar
     * @return un arreglo solo de los elemntos filtrados 
     */
    public static ArrayList<Medicamento> filtrarListaPor(String keyword, ArrayList<Medicamento> lista) {
        ArrayList<Medicamento> listaFilatrada = new ArrayList();
        keyword = keyword.toLowerCase();
        for (Medicamento medicamento : lista) {
            if (medicamento.getSustancia().toLowerCase().contains(keyword)
                    || (medicamento.getClave().contains(keyword))) {
                listaFilatrada.add(medicamento);
            }
        }
        return listaFilatrada;
    }
    /**
     * Metodo que ordena la lista de medicamentos por un parametro determinado
     * @param param parametro por el que se desea ordenar la lista
     * @param lista lista a ordenar
     * @return lista ordenada
     */

    public static ArrayList<Medicamento> odernarListaPor(String param, ArrayList<Medicamento> lista) {
        ArrayList<Medicamento> listaOrdenada;
        switch (param) {
            case "CLAVE":
                listaOrdenada = ordenarPorClave(lista);
                break;
            default:
                listaOrdenada = ordenarPorSustancia(lista);
                break;
        }
        return listaOrdenada;
    }
    /**
     * Metodo que ordena a los medicamentos por sustancia
     * @param lista lista a ordenar 
     * @return lista ordenada por sustancia
     */

    private static ArrayList<Medicamento> ordenarPorSustancia(ArrayList<Medicamento> lista) {
        ArrayList<Medicamento> listaOrdenada = lista;
        int size = listaOrdenada.size();
        for (int j = 1; j < size; j++) {
            Medicamento current = listaOrdenada.get(j);
            String currentstr = current.getSustancia();
            int i = j - 1;
            while ((i > -1) && (listaOrdenada.get(i).getSustancia().compareToIgnoreCase(currentstr) > 0)) {
                listaOrdenada.set(i + 1, listaOrdenada.get(i));
                i--;
            }
            listaOrdenada.set(i + 1, current);
        }
        return listaOrdenada;
    }
    /**
     * Metodo que ordena a los medicamentos por clave 
     * @param lista lista a ordenar 
     * @return lista ordenada por clave
     */

    private static ArrayList<Medicamento> ordenarPorClave(ArrayList<Medicamento> lista) {

        ArrayList<Medicamento> listaOrdenada = lista;
        int size = listaOrdenada.size();
        for (int j = 1; j < size; j++) {
            Medicamento current = listaOrdenada.get(j);
            String currentstr = current.getClave();
            int i = j - 1;
            while ((i > -1) && (listaOrdenada.get(i).getClave().compareToIgnoreCase(currentstr) > 0)) {
                listaOrdenada.set(i + 1, listaOrdenada.get(i));
                i--;
            }
            listaOrdenada.set(i + 1, current);
        }
        return listaOrdenada;
    }
}
