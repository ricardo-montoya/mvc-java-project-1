/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.MedicamentoDAO;
import data.Medicamento;
import java.util.ArrayList;
import model.MedicamentoEstadisticas;

/**
 *
 * @author Diego R
 */
public class ControllerMedicamento {

    public ArrayList<Medicamento> obtenerMedicamentos() {
        return new MedicamentoDAO().obtenerMedicamentos();
    }

    public ArrayList<Medicamento> obtenerMedicamentos(String where) {
        return new MedicamentoDAO().obtenerMedicamentos(where);

    }

    public Medicamento obtenerMedicamento(String clave){
        return new MedicamentoDAO().obtenerMedicamento(clave);
    }

    public boolean insertarMedicamento(Medicamento medicamento) {
        return new MedicamentoDAO().insertarMedicamento(medicamento);
    }

    public boolean eliminarMedicamento(String clave) {
        return new MedicamentoDAO().eliminarMedicamento(clave);
    }

    public int obtenerNumeroTotalMedicamentos(){
        return new MedicamentoEstadisticas().obtenerNumeroTotalMedicamentos();
    }
    
    public int obtenerNumeroMedicamentosporTipo(String tipo){
        return new MedicamentoEstadisticas().obtenerNumeroMedicamentosporTipo(tipo);
    }

}
