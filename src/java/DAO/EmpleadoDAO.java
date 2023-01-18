package DAO;

import data.Empleado;
import data.Sexo;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import data.DBConnector;

/**
 * Clase que permite realizar acciones sobre la base de datos
 * @author Diego Ricardo Montoya Baez
 * @version 1.2
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html">Documentación
 * del paquete SQL</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/package-summary.html">Documentación
 * del paquete UTIL</a>
 */
public class EmpleadoDAO {
    private DBConnector conexion;
    
    private static final String TABLA = "empleados";

    public EmpleadoDAO() {
        conexion = DBConnector.getInstance();
        System.out.println(this.conexion.conectar());
    }

    /**
     * Metodo que realiza la conexion a la base de datos mediante el metodo class.forNAme
     * @return un valor booleano del estado de la conexion
     */
    public final boolean conectar() {
        return conexion.conectar();
        
    }
    /**
     * Metodo que realiza la desconexion de la base de datos
     * @return el estado de la desconexion a la base de datos
     */

    public boolean desconectar() {
        return this.conexion.desconectar();
    }
    /**
     * Metodo que obtiene la informacion de los empleados de la base de datos
     * @return una lista de empleados
     */

    public ArrayList<Empleado> obtenerEmpleados() {
        ArrayList<Empleado> empleados;
        empleados = ejecutarQueryGET();
        return empleados;
    }
    /**
     * Metodo que obtiene la informacion de los empleados de la base de datos con un criterio de selecion
     * @param where criterio de seleccion
     * @return una lista de  empleados con un criterio de seleccion
     */
    public ArrayList<Empleado> obtenerEmpleados(String where) {
        ArrayList<Empleado> empleados;
        empleados = ejecutarQueryGET(where);
        return empleados;
    }
    /**
     * Metodo que  modela y ejecuta el query para la peticion a la base de datos
     * @return Lista de datos proveniente de la tabla empleados
     */

    private ArrayList<Empleado> ejecutarQueryGET() {
        ArrayList<Empleado> listaObtenida = new ArrayList();
        String query = "SELECT * FROM " + TABLA + " ORDER BY salario DESC";
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
     * @return Lista de datos proveniente de la tabla empleados con un criterio de seleccion
     */
    
    private ArrayList<Empleado> ejecutarQueryGET(String where) {
        ArrayList<Empleado> listaObtenida = new ArrayList();
        String query = "SELECT * FROM " + TABLA + " WHERE "+ where;
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
     * @return lista de empleados con un mapeo de su estructura
     * @throws SQLException 
     */
    private ArrayList<Empleado> mapearResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Empleado> listaDeEmpleados = new ArrayList();
        while (resultSet.next()) {
            Empleado empleadoTemp = new Empleado();
            empleadoTemp.setEdad(Integer.parseInt(resultSet.getString("edad")));
            empleadoTemp.setNombre(resultSet.getString("nombre"));
            empleadoTemp.setNumEmpleado(Long.parseLong(resultSet.getString("numEmpleado")));
            empleadoTemp.setSalario(Double.parseDouble(resultSet.getString("salario")));
            empleadoTemp.setSexo(mapSexo(resultSet.getString("sexo")));
            listaDeEmpleados.add(empleadoTemp);

        }
        return listaDeEmpleados;

    }
    
    /**
     * Metodo que obtiene la informacion de los empleados de la base de datos con el criterio de seleccion numero de empleado
     * @param numEmpleado numero de empleado
     * @return una lista de  empleados con un criterio de seleccion igual al numero de empleado
     */

    public Empleado obtenerEmpleado(String numEmpleado) {
        Empleado empleado = new Empleado();
        String query = "SELECT * FROM " + TABLA + " WHERE numEmpleado = ?";
        PreparedStatement ps;
        try {
            ps = conexion.getConnection().prepareStatement(query);
            ps.setString(1, numEmpleado);
            
            ResultSet rs = ps.executeQuery();
            empleado = mapearEmpleardo(rs);
        } catch (SQLException ex) {
            System.out.println("Error: " +ex.getLocalizedMessage());
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleado;
    }
    
    /**
     * Metodo que genera un mapeo sobre la estructura de los datos de los empleados
     * @param resultSet estructura de los datos
     * @return lista de empleados con un mapeo de su estructura
     * @throws SQLException 
     */
    private Empleado mapearEmpleardo(ResultSet resultSet) throws SQLException{
        Empleado empleadoTemp = new Empleado();
        while (resultSet.next()) {
            empleadoTemp.setEdad(Integer.parseInt(resultSet.getString("edad")));
            empleadoTemp.setNombre(resultSet.getString("nombre"));
            empleadoTemp.setNumEmpleado(Long.parseLong(resultSet.getString("numEmpleado")));
            empleadoTemp.setSalario(Double.parseDouble(resultSet.getString("salario")));
            empleadoTemp.setSexo(mapSexo(resultSet.getString("sexo")));
        }
        return empleadoTemp;
    }
    /**
     * Metodo que inserta un empleado en la base de datos
     * @param empleado datos del empleado a insertar
     * @return estado de la insercion
     */

    public boolean insertarEmpleado(Empleado empleado) {
        boolean estado = false;
        PreparedStatement ps = crearPreparedStatementInsercion(empleado);
        estado = ejecutarInsercion(ps);
        return estado;
    }
    /**
     * Metodo que modela el query para la insercion en la base de datos
     * @param empleado datos del empleado a insertar
     * @return query para la insercion a la base de datos
     */

    private PreparedStatement crearPreparedStatementInsercion(Empleado empleado) {
        String query = "INSERT INTO " + TABLA + " VALUES(?, ?, ?, ?, ?) ";
        PreparedStatement ps = null;
        try {
            ps = conexion.getConnection().prepareStatement(query);
            ps.setString(1, "" + empleado.getNumEmpleado());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, "" + empleado.getEdad());
            ps.setString(4, empleado.getSexo().name());
            ps.setString(5, "" + empleado.getSalario());
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
    /**
     * Metodo que realiza la actualizacion de un empleado en la base de datos
     * @param numEmpleado numero de empleado a actualiza
     * @param empleadoModificado empleado a modificar
     * @return estado de la actualizacion
     */

    public boolean actualizarEmpleado(long numEmpleado, Empleado empleadoModificado) {
        boolean estado = false;
        PreparedStatement ps = crearPreparedStatementActualizacion(numEmpleado, empleadoModificado);
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
     * @param empleado datos del empleado a actualizar
     * @return query para la actualizacion a la base de datos
     */
    

    private PreparedStatement crearPreparedStatementActualizacion(long numEmpleado, Empleado empleadoModificado) {
        String query = "UPDATE " + TABLA + " SET numEmpleado = ?, nombre = ?,"
                + "edad = ?, sexo = ?,salario =  ? WHERE numEmpleado = ?";
        PreparedStatement ps = null;
        try {
            ps = conexion.getConnection().prepareStatement(query);
            ps.setString(1, "" + empleadoModificado.getNumEmpleado());
            ps.setString(2, empleadoModificado.getNombre());
            ps.setString(3, "" + empleadoModificado.getEdad());
            ps.setString(4, empleadoModificado.getSexo().name());
            ps.setString(5, "" + empleadoModificado.getSalario());
            ps.setString(6, "" + numEmpleado);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Eroor: " + ex.getLocalizedMessage());
        }
        return ps;
    }
    /**
     * Metodo que elimina a un empleado de la base de datos
     * @param numEmpleado numero del empleado a eliminar
     * @return estado de la eliminacion
     */

    public boolean eliminarEmpleado(long numEmpleado) {
        boolean estado = false;
        PreparedStatement ps = crearPreparedStatementEliminar(numEmpleado);
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
     * @param numEmpleado datos del empleado a eliminar
     * @return query para la eliminacion en la base de datos
     */
    

    private PreparedStatement crearPreparedStatementEliminar(long numEmpleado) {
        String query = "DELETE FROM " + TABLA + " WHERE numEmpleado = ?";
        PreparedStatement ps = null;
        try {
            ps = conexion.getConnection().prepareStatement(query);
            ps.setString(1, "" + numEmpleado);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }

    /**
     * Convierte la cadena obtenida de la base de datos a un objeto de la
     * Enumeracion "Sexo"
     *
     * @param cadena Cadena obtenida de la base de datos
     * @return El objeto más adecuado (coincidente) segun al parametro pasado
     */
    public Sexo mapSexo(String cadena) {
        cadena = cadena.toLowerCase();
        Sexo sexo = Sexo.Otro;
        if (cadena.equals("masculino")) {
            sexo = Sexo.Masculino;
        }
        if (cadena.equals("femenino")) {
            sexo = Sexo.Femenino;
        }
        return sexo;
    }

}