/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que realiza la conexion y desconexion a la base de datos
 * @author Diego Ricardo Montoya Baez
 * @version 1.2
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html">Documentación
 * del paquete SQL</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/package-summary.html">Documentación
 * del paquete UTIL</a>
 */
public class DBConnector {
    
    // Static variable reference of single_instance
    // of type DBConnector
    private static DBConnector single_instance = null;
    private DBConnector() {
        System.out.println(this.conectar());
    }

    // Static method
    // Static method to create instance of DBConnector class
    public static DBConnector getInstance() {
        if (single_instance == null) {
            single_instance = new DBConnector();
        }

        return single_instance;
    }
    private static final String USUARIO = "root";
    private static final String PSWD = "gamma";
    private static final String BD = "hmm"; //TODO: Cambiar la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private Connection conexion;

    
    public Connection getConnection(){
        return this.conexion;
    }
    /**
     * Metodo que realiza la conexion a la base de datos mediante el metodo class.forNAme
     * @return un valor booleano del estado de la conexion
     */
    public final boolean conectar() {
        boolean estado = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL + BD, USUARIO, PSWD);
            if (conexion != null) {
                estado = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return estado;
    }
    /**
     * Metodo que realiza la desconexion de la base de datos
     * @return el estado de la desconexion a la base de datos
     */

    public boolean desconectar() {
        boolean estado = false;
        try {
            conexion.close();
            estado = true;
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return estado;
    }
}
