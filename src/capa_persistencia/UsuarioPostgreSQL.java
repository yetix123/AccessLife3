/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa_persistencia;

import capa_dominio.Usuario;
import capa_exceptions.ExceptionsAll;
import java.sql.*;

/**
 *
 * @author estdi
 */
public class UsuarioPostgreSQL {

    private final AccesoDatosJDBC accesoDatosJDBC;

    public UsuarioPostgreSQL(AccesoDatosJDBC accesoDatosJDBC) {
        this.accesoDatosJDBC = accesoDatosJDBC;
    }

    public void guardarUsuario(Usuario usuario) throws ExceptionsAll.BaseDeDatosException {
        String insertSQL = "INSERT INTO usuario(users, contraseña, rol) "
                + "VALUES (?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(insertSQL);
            sentencia.setString(1, usuario.getUser());
            sentencia.setString(2, usuario.getContraseña());
            sentencia.setString(3, usuario.getRol());
            sentencia.executeUpdate();
        } catch (Exception e) {
            throw new ExceptionsAll.BaseDeDatosException("ERROR AL INTENTAR GUARDAR USUARIO" + e);
        }
    }

    public boolean verificarCredenciales(String user, String contraseña) throws ExceptionsAll.BaseDeDatosException{
        String consultaSQL = "SELECT users, contraseña FROM usuario WHERE users = ? AND contraseña = ?";
        try {
            PreparedStatement sentencia = accesoDatosJDBC.prepararSentencia(consultaSQL);
            sentencia.setString(1, user);
            sentencia.setString(2, contraseña);
            ResultSet resultado = sentencia.executeQuery();
            return resultado.next();
        } catch (Exception e) {
            throw new ExceptionsAll.BaseDeDatosException("ERROR AL INTENTAR VERIFICAR CREDENCIALES" + e);            
        }
    }

}
