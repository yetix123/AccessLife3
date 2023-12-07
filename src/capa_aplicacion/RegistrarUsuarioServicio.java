/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa_aplicacion;

import capa_dominio.*;
import capa_persistencia.*;
import capa_exceptions.ExceptionsAll;

/**
 *
 * @author estdi
 */
public class RegistrarUsuarioServicio {

    private AccesoDatosJDBC accesoDatosJDBC ;
    private UsuarioPostgreSQL usuarioPostgreSQL;

    public RegistrarUsuarioServicio() {
        accesoDatosJDBC = new AccesoDatosJDBCPostgreSQL();
        usuarioPostgreSQL = new UsuarioPostgreSQL(accesoDatosJDBC);
    }
    
    public boolean verificarCuenta(String user, String contraseña) throws ExceptionsAll.BaseDeDatosException {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        boolean verificacion = usuarioPostgreSQL.verificarCredenciales(user, contraseña);
        accesoDatosJDBC.terminarTransaccion();
        return verificacion;
    }

    public void guardarUsuario(Usuario usuario) throws Exception {

        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        usuarioPostgreSQL.guardarUsuario(usuario);
        accesoDatosJDBC.terminarTransaccion();
    }

}
