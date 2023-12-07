/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa_aplicacion;

import capa_dominio.Dentista;
import capa_persistencia.*;
import capa_exceptions.ExceptionsAll;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author estdi
 */
public class RegistrarDentistaServicio {
    
    private AccesoDatosJDBC accesoDatosJDBC;
    private DentistaPostgreSQL dentistaPostgreSQL;

    public RegistrarDentistaServicio() {
        accesoDatosJDBC = new AccesoDatosJDBCPostgreSQL();
        dentistaPostgreSQL = new DentistaPostgreSQL(accesoDatosJDBC);        
    }
    
    public int incrementarID() throws Exception{
        int id;
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        id = dentistaPostgreSQL.idIncrementableDentista();
        accesoDatosJDBC.terminarTransaccion();
        return id;
    }

    public void mostrarTablaDentista(DefaultTableModel modelo) throws ExceptionsAll.BaseDeDatosException {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        dentistaPostgreSQL.mostrarDentistaEnMiTabla(modelo);
        accesoDatosJDBC.terminarTransaccion();
    }
    
    public Dentista buscarDentista(String nombres) throws ExceptionsAll.BaseDeDatosException {
        accesoDatosJDBC.abrirConexion();
        Dentista dentista = dentistaPostgreSQL.buscarDentistaPorNombres(nombres);
        accesoDatosJDBC.cerrarConexion();
        return dentista;
    }    
    
    public void guardarDentista(Dentista dentista) throws Exception {
        
        //----- VALIDAMOS REGLAS DE NEGOCIO -----
        if(!dentista.validarFormatoNombres()){            
            throw new ExceptionsAll.ValidacionException("EL LIMITE DE 30 CARACTERES SE HA SUPERADO.");
        }
        
        if(!dentista.validarFormatoDni()){            
            throw new ExceptionsAll.ValidacionException("EL DNI DEBE TENER 8 DIGITOS");
        }
        
        if(!dentista.validarFormatoTelefono()){            
            throw new ExceptionsAll.ValidacionException("EL TELEFONO DEBE TENER 9 DIGITOS");
        }
        
        if(!dentista.validarFormatoEmail()){            
            throw new ExceptionsAll.ValidacionException("EL FORMATO DE EMAIL ES INVALIDO, INGRESE 1 CARACTER ANTES DEL FORMATO CORRECTO -----> @gmail.com o @hotmail.com <-----");
        }
        
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        dentistaPostgreSQL.guardarDentista(dentista);
        accesoDatosJDBC.terminarTransaccion();                
    }
    
}
