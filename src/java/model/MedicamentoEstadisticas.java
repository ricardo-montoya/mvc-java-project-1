/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.Medicamento;
import DAO.MedicamentoDAO;
import java.util.ArrayList;
/**
 * Clase que permite calcular las estadisticas de los medicamentos
 * @author Diego Ricardo Montoya Baez, Gamaliel Omar Marquez Villaseñor, Hernández Vásquez Fatima Montserrat
 * @version 1.0
 */
public class MedicamentoEstadisticas {
    
    /**
     * Metodo que calcula el numero total de medicamentos
     * @return el numero total de medicamentos
     */
    public int obtenerNumeroTotalMedicamentos(){
        return new MedicamentoDAO().obtenerMedicamentos().size();
    }
    /**
     * Metodo que calcula la cantidad de medicamentos por tipo de administracion
     * @param tipo el tipo de via de administracion
     * @return la cantidad de medicamentos de este tipo de administracion
     */
    
    public int obtenerNumeroMedicamentosporTipo(String tipo){
        ArrayList<Medicamento> list= new MedicamentoDAO().obtenerMedicamentos("administracion LIKE '" + tipo +"'");
        return list.size();
    }
}
