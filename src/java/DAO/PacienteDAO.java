/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import data.DBConnector;
import data.Paciente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que permite realizar acciones sobre la base de datos
 * @author Diego Ricardo Montoya Baez
 * @version 1.2
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html">Documentación
 * del paquete SQL</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/package-summary.html">Documentación
 * del paquete UTIL</a>
 */
public class PacienteDAO {

    private static final String TABLA = "pacientes";
    private DBConnector conexion;
    
      public PacienteDAO() {
          conexion = DBConnector.getInstance();
        System.out.println(this.conexion.conectar());
    }


    /**
     * Metodo que obtiene la informacion de los pacientes de la base de datos
     * @return una lista de pacientes
     */
    public ArrayList<Paciente> obtenerPacientes() {
        ArrayList<Paciente> pacientes;
        pacientes = ejecutarQueryGET();
        return pacientes;
    }

    /**
     * Metodo que obtiene la informacion de los pacientes de la base de datos con un criterio de selecion
     * @param where criterio de seleccion
     * @return una lista de pacientes con un criterio de seleccion
     */
    public ArrayList<Paciente> obtenerPacientes(String where) {
        ArrayList<Paciente> pacientes;
        pacientes = ejecutarQueryGET(where);
        return pacientes;
    }

    /**
     * Metodo que  modela y ejecuta el query para la peticion a la base de datos
     * @return Lista de datos proveniente de la tabla pacientes
     */
    private ArrayList<Paciente> ejecutarQueryGET() {
        ArrayList<Paciente> listaObtenida = new ArrayList();
        String query = "SELECT * FROM " + TABLA;
        try {
            ResultSet resultSet = conexion.getConnection().createStatement().executeQuery(query);
            listaObtenida = mapearResultSet(resultSet);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return listaObtenida;
    }

    /**
     * Metodo que  modela y ejecuta el query para la peticion a la base de datos con un citerio de seleccion
     * @param where criterio de seleccion
     * @return Lista de datos proveniente de la tabla pacientes con un criterio de seleccion
     */
    private ArrayList<Paciente> ejecutarQueryGET(String where) {
        ArrayList<Paciente> listaObtenida = new ArrayList();
        String query = "SELECT * FROM " + TABLA + " WHERE " + where;
        try {
            ResultSet resultSet = conexion.getConnection().createStatement().executeQuery(query);
            listaObtenida = mapearResultSet(resultSet);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return listaObtenida;
    }

    /**
     * Metodo que genera un mapeo sobre la estructura de los datos
     * @param resultSet estructura de los datos
     * @return lista de pacientes con un mapeo de su estructura
     * @throws SQLException 
     */
    private ArrayList<Paciente> mapearResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Paciente> listaDePacientes = new ArrayList();
        while (resultSet.next()) {
            Paciente paciente = new Paciente();
            paciente.setPacienteNSS(resultSet.getString("pacienteNSS"));
            paciente.setNombre(resultSet.getString("nombre"));
            paciente.setEdad(Double.parseDouble(resultSet.getString("edad")));
            paciente.setDiagnostico(resultSet.getString("diagnostico"));
            listaDePacientes.add(paciente);
        }
        return listaDePacientes;
    }
     /**
     * Metodo que obtiene la informacion de los pacieentes de la base de datos con el criterio de seleccion NSS
     * @param pacienteNSS NSS
     * @return una lista de  empleados con un criterio de seleccion igual al NSS
     */
    
     public Paciente obtenerPaciente(String pacienteNSS) {
        Paciente paciente = new Paciente();
        String query = "SELECT * FROM " + TABLA + " WHERE pacienteNSS = ?";
        PreparedStatement ps;
        try {
            ps = conexion.getConnection().prepareStatement(query);
            ps.setString(1, pacienteNSS);
            
            ResultSet rs = ps.executeQuery();
            paciente = mapearPaciente(rs);
        } catch (SQLException ex) {
            System.out.println("Error: " +ex.getLocalizedMessage());
        }
        return paciente;
    }
     /**
     * Metodo que genera un mapeo sobre la estructura de los datos de los pacientes
     * @param resultSet estructura de los datos
     * @return lista de pacientes con un mapeo de su estructura
     * @throws SQLException 
     */
    
    private Paciente mapearPaciente(ResultSet resultSet) throws SQLException{
        Paciente paciente = new Paciente();
        while (resultSet.next()) {
            paciente.setPacienteNSS(resultSet.getString("pacienteNSS"));
            paciente.setNombre(resultSet.getString("nombre"));
            paciente.setEdad(Double.parseDouble(resultSet.getString("edad")));
            paciente.setDiagnostico(resultSet.getString("diagnostico"));
        }
        return paciente;
    }

    /**
     * Metodo que inserta un paciente en la base de datos
     * @param paciente datos del paciente a insertar
     * @return estado de la insercion
     */
    public boolean insertarPaciente(Paciente paciente) {
        boolean estado = false;
        PreparedStatement ps = crearPreparedStatementInsercion(paciente);
        try {
            ps.execute();
            estado = true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return estado;
    }

    /**
     * Metodo que modela el query para la insercion en la base de datos
     * @param paciente datos del paciente a insertar
     * @return query para la insercion a la base de datos
     */
    private PreparedStatement crearPreparedStatementInsercion(Paciente paciente) {
        String query = "INSERT INTO " + TABLA + " VALUES(?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conexion.getConnection().prepareStatement(query);
            ps.setString(1, paciente.getPacienteNSS());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, "" + paciente.getEdad());
            ps.setString(4, paciente.getDiagnostico());
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return ps;
    }

    /**
     * Metodo que realiza la actualizacion de un paciente en la base de datos
     * @param pacienteNSS NSS a modificar
     * @param pacienteModificado paciente a modificar
     * @return estado de la actualizacion
     */
    public boolean actualizarPaciente(String pacienteNSS, Paciente pacienteModificado) {
        boolean estado = false;
        PreparedStatement ps = crearPreparedStatementActualizacion(pacienteNSS, pacienteModificado);
        try {
            ps.execute();
            estado = true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return estado;
    }

    /**
     * Metodo que modela el query para la actualizacion en la base de datos
     * @param paciente datos del empleado a actualizar
     * @return query para la actualizacion a la base de datos
     */
    private PreparedStatement crearPreparedStatementActualizacion(String pacienteNSS, Paciente pacienteModificado) {
        String query = "UPDATE " + TABLA + " SET pacienteNSS=?, nombre=?, edad=?, diagnostico=? WHERE pacienteNSS=?";
        PreparedStatement ps = null;
        try {
            ps = conexion.getConnection().prepareStatement(query);
            ps.setString(1, pacienteModificado.getPacienteNSS());
            ps.setString(2, pacienteModificado.getNombre());
            ps.setString(3, "" + pacienteModificado.getEdad());
            ps.setString(4, pacienteModificado.getDiagnostico());
            ps.setString(5, pacienteNSS);
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
        }
        return ps;
    }

    /**
     * Metodo que elimina a un paciente de la base de datos
     * @param pacienteNSS NSS del paciente
     * @return estado de la eliminacion
     */
    public boolean eliminarPaciente(String pacienteNSS) {
        boolean estado = false;
        PreparedStatement ps = crearPreparedStatementEliminar(pacienteNSS);
        try {
            ps.execute();
            estado = true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return estado;
    }

    /**
     * Metodo que modela el query para la eliminacion en la base de datos
     * @param pacienteNSS datos del paciente a eliminar
     * @return query para la eliminacion en la base de datos
     */
    private PreparedStatement crearPreparedStatementEliminar(String pacienteNSS) {
        String query = "DELETE FROM " + TABLA + " WHERE pacienteNSS=?";
        PreparedStatement ps = null;
        try {
            ps = conexion.getConnection().prepareStatement(query);
            ps.setString(1, pacienteNSS);
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
        }
        return ps;
    }
}
