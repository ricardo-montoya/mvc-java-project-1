package DAO;

import data.DBConnector;
import data.Medicamento;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que permite realizar acciones sobre la base de datos
 * @author Diego Ricardo Montoya Baez
 * @version 1.2
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html">Documentación
 * del paquete SQL</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/package-summary.html">Documentación
 * del paquete UTIL</a>
 */
public class MedicamentoDAO {
    DBConnector conexion;
    private static final String TABLA = "medicamentos";

    public MedicamentoDAO() {
        conexion = DBConnector.getInstance();
        System.out.println(this.conexion.conectar());
    }
    /**
     * Metodo que obtiene la informacion de los medicamentos de la base de datos
     * @return una lista de medicamentos
     */

    public ArrayList<Medicamento> obtenerMedicamentos() {
        ArrayList<Medicamento> medicamentos;
        medicamentos = ejecutarQueryGET();
        return medicamentos;
    }
    /**
     * Metodo que obtiene la informacion de los medicamentos de la base de datos con un criterio de selecion
     * @param where criterio de seleccion
     * @return una lista de medicamentos con un criterio de seleccion
     */

    public ArrayList<Medicamento> obtenerMedicamentos(String where) {
        ArrayList<Medicamento> medicamentos;
        medicamentos = ejecutarQueryGET(where);
        return medicamentos;
    }
    /**
     * Metodo que  modela y ejecuta el query para la peticion a la base de datos
     * @return Lista de datos proveniente de la tabla medicamentos
     */

    private ArrayList<Medicamento> ejecutarQueryGET() {
        ArrayList<Medicamento> listaObtenida = new ArrayList();
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
     * @return Lista de datos proveniente de la tabla medicamentos con un criterio de seleccion
     */

    private ArrayList<Medicamento> ejecutarQueryGET(String where) {
        ArrayList<Medicamento> listaObtenida = new ArrayList();
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
     * @return lista de medicamentos con un mapeo de su estructura
     * @throws SQLException 
     */

    private ArrayList<Medicamento> mapearResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Medicamento> listaDeMedicamentos = new ArrayList();
        while (resultSet.next()) {
            Medicamento medicamentoTemp = new Medicamento();
            medicamentoTemp.setClave(resultSet.getString("clave"));
            medicamentoTemp.setNombre(resultSet.getString("nombre"));
            medicamentoTemp.setSustancia(resultSet.getString("sustancia"));
            medicamentoTemp.setPresentacion(resultSet.getString("presentacion"));
            medicamentoTemp.setAdministracion(resultSet.getString("administracion"));
            listaDeMedicamentos.add(medicamentoTemp);
        }
        return listaDeMedicamentos;

    }
    /**
     * Metodo que obtiene la informacion de los medicamentos de la base de datos con el criterio de seleccion clave
     * @param clave numero de empleado
     * @return una lista de medicamentos con un criterio de seleccion igual a su calve
     */

    public Medicamento obtenerMedicamento(String clave) {
        Medicamento medicamento = new Medicamento();
        String query = "SELECT * FROM " + TABLA + " WHERE clave = ?";
        PreparedStatement ps;
        try {
            ps = conexion.getConnection().prepareStatement(query);
            ps.setString(1, clave);
            ResultSet rs = ps.executeQuery();
            medicamento = mapearMedicamento(rs);
        } catch (SQLException ex) {
            System.out.println("Error: " +ex.getLocalizedMessage());
            Logger.getLogger(MedicamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicamento;
    }
    /**
     * Metodo que genera un mapeo sobre la estructura de los datos de los medicamentos
     * @param resultSet estructura de los datos
     * @return lista de medicamentos con un mapeo de su estructura
     * @throws SQLException 
     */

    private Medicamento mapearMedicamento(ResultSet resultSet) throws SQLException {
        Medicamento medicamentoTemp = new Medicamento();
        while (resultSet.next()) {
            medicamentoTemp.setClave(resultSet.getString("clave"));
            medicamentoTemp.setNombre(resultSet.getString("nombre"));
            medicamentoTemp.setSustancia(resultSet.getString("sustancia"));
            medicamentoTemp.setPresentacion(resultSet.getString("presentacion"));
            medicamentoTemp.setAdministracion(resultSet.getString("administracion"));
        }
        return medicamentoTemp;

    }
    /**
     * Metodo que inserta un medicamento en la base de datos
     * @param medicamento datos del empleado a insertar
     * @return estado de la insercion
     */

    public boolean insertarMedicamento(Medicamento medicamento) {
        boolean estado = false;
        PreparedStatement ps = crearPreparedStatementInsercion(medicamento);
        estado = ejecutarInsercion(ps);
        return estado;
    }
    /**
     * Metodo que modela el query para la insercion en la base de datos
     * @param medicamento datos del medicamento a insertar
     * @return query para la insercion a la base de datos
     */

    private PreparedStatement crearPreparedStatementInsercion(Medicamento medicamento) {
        String query = "INSERT INTO " + TABLA + " VALUES(?, ?, ?, ?, ?) ";
        PreparedStatement ps = null;
        try {
            ps = conexion.getConnection().prepareStatement(query);
            ps.setString(1, medicamento.getClave());
            ps.setString(2, medicamento.getNombre());
            ps.setString(3, medicamento.getSustancia());
            ps.setString(4, medicamento.getPresentacion());
            ps.setString(5, medicamento.getAdministracion());
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return ps;
    }
    /**
     * Metodo que ejecuta el query para la insercion en la base de datos
     * @param preparedStatement query para la insercion
     * @return estado de la insercion
     */

    private boolean ejecutarInsercion(PreparedStatement preparedStatement) {
        boolean estado = false;
        try {
            preparedStatement.execute();
            estado = true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return estado;
    }
    

    public boolean eliminarMedicamento(String Clave) {
        boolean estado = false;
        PreparedStatement ps = crearPreparedStatementEliminar(Clave);
        try {
            ps.execute();
            estado = true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return estado;
    }
    /**
     * Metodo que elimina un medicamento de la base de datos
     * @param clave calave del medicamento
     * @return estado de la eliminacion
     */

    private PreparedStatement crearPreparedStatementEliminar(String Clave) {
        String query = "DELETE FROM " + TABLA + " WHERE clave = ?";
        PreparedStatement ps = null;
        try {
            ps = conexion.getConnection().prepareStatement(query);
            ps.setString(1, Clave);
        } catch (SQLException ex) {
        }
        return ps;
    }

}
