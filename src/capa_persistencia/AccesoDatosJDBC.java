package capa_persistencia;

import capa_exceptions.ExceptionsAll;
import java.sql.*;

/**
 *
 * @author estdi
 */
public abstract class AccesoDatosJDBC {

    protected Connection conexion;

    public abstract void abrirConexion() throws ExceptionsAll.BaseDeDatosException;

    public void cerrarConexion() throws ExceptionsAll.BaseDeDatosException {
        try {
            conexion.close();
        } catch (SQLException e) {
            throw new ExceptionsAll.BaseDeDatosException("Ocurrio un problema al cerrar la conexión" + e);
        }

    }

    public void iniciarTransaccion() throws ExceptionsAll.BaseDeDatosException {
        try {
            conexion.setAutoCommit(false);
        } catch (SQLException e) {
            throw new ExceptionsAll.BaseDeDatosException("Ocurrio un problema al iniciar la conexión" + e);
        }
    }

    public void terminarTransaccion() throws ExceptionsAll.BaseDeDatosException {
        try {
            conexion.commit();
            conexion.setAutoCommit(true);
            conexion.close();
        } catch (SQLException e) {
            throw new ExceptionsAll.BaseDeDatosException("Ocurrio un problema al terminar la transaccion" + e);
        }
    }

    public void cancelarTransaccion() throws ExceptionsAll.BaseDeDatosException {
        try {
            conexion.rollback();
            conexion.setAutoCommit(true);
            conexion.close();
        } catch (SQLException e) {
            throw new ExceptionsAll.BaseDeDatosException("Ocurrio un problema al cancelar la conexión" + e);
        }
    }

    public PreparedStatement prepararSentencia(String sql) throws ExceptionsAll.BaseDeDatosException {
        try {
            return conexion.prepareStatement(sql);
        } catch (SQLException e) {
            throw new ExceptionsAll.BaseDeDatosException("Ocurrio un problema al preparar la sentencia" + e);
        }
    }

    public ResultSet ejecutarConsulta(String sql) throws ExceptionsAll.BaseDeDatosException {
        try {
            Statement sentencia;
            ResultSet resultado;
            sentencia = conexion.createStatement();
            sentencia.close();
            resultado = sentencia.executeQuery(sql);
            return resultado;
        } catch (SQLException e) {
            throw new ExceptionsAll.BaseDeDatosException("Ocurrio un problema al ejecutar la consulta" + e);
        }
    }
}
