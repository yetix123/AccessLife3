package capa_persistencia;

import java.sql.DriverManager;
import capa_exceptions.ExceptionsAll.BaseDeDatosException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author estdi
 */
public class AccesoDatosJDBCPostgreSQL extends AccesoDatosJDBC {

//    @Override
//    public void abrirConexion() throws BaseDeDatosException {
//        try {
//            Class.forName("org.postgresql.Driver");
//            String url = "jdbc:postgresql://localhost:5432/AccessV2";
//            conexion = DriverManager.getConnection(url, "postgres", "krhizpostgresql");
//        } catch (Exception e) {
//            throw new UnsupportedOperationException("Ocurrio un problema en la conexion con la base de datos" + e); 
//        }        
//    }
@Override
public void abrirConexion() throws BaseDeDatosException {
    Properties prop = new Properties();
    InputStream input = null;
    try {
        input = getClass().getClassLoader().getResourceAsStream("database.properties");
        prop.load(input);

        String url = prop.getProperty("url");
        String usuario = prop.getProperty("usuario");
        String password = prop.getProperty("password");
        String driver = prop.getProperty("driver");

        Class.forName(driver);
        conexion = DriverManager.getConnection(url, usuario, password);
    } catch (IOException | ClassNotFoundException | SQLException e) {
        throw new BaseDeDatosException("Ocurrió un problema en la conexión con la base de datos" + e); 
    }      
}

}
