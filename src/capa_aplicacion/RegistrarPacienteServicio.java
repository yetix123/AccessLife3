/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa_aplicacion;

import capa_dominio.Paciente;
import capa_persistencia.*;
import capa_exceptions.ExceptionsAll;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author estdi
 */
public class RegistrarPacienteServicio {
    
    private AccesoDatosJDBC accesoDatosJDBC;
    private PacientePostgreSQL pacientePostgreSQL;

    public RegistrarPacienteServicio() {
        accesoDatosJDBC = new AccesoDatosJDBCPostgreSQL();
        pacientePostgreSQL = new PacientePostgreSQL(accesoDatosJDBC);        
    }
    
     public int incrementarID() throws Exception{
        int id;
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        id = pacientePostgreSQL.idIncrementablePaciente();
        accesoDatosJDBC.terminarTransaccion();
        return id;
    }

    public void mostrarTablaPaciente(DefaultTableModel modelo) throws ExceptionsAll.BaseDeDatosException {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        pacientePostgreSQL.mostrarPacienteEnMiTabla(modelo);
        accesoDatosJDBC.terminarTransaccion();
    }
    
    public void guardarPaciente(Paciente paciente) throws Exception {
        //----- VALIDAMOS REGLAS DE NEGOCIO -----
        
        if(!paciente.validarFormatoNombres()){            
            throw new ExceptionsAll.ValidacionException("EL LIMITE DE 30 CARACTERES SE HA SUPERADO.");
        }
        
        if(!paciente.validarFormatoDni()){            
            throw new ExceptionsAll.ValidacionException("EL DNI DEBE TENER 8 DIGITOS");
        }
        
        if(!paciente.validarFormatoTelefono()){            
            throw new ExceptionsAll.ValidacionException("EL TELEFONO DEBE TENER 9 DIGITOS");
        }
        
        if(!paciente.validarFormatoEmail()){            
           throw new ExceptionsAll.ValidacionException("EL FORMATO DE EMAIL ES INVALIDO, INGRESE 1 CARACTER ANTES DEL FORMATO CORRECTO -----> @gmail.com o @hotmail.com <-----");
        }
        
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        pacientePostgreSQL.guardarPaciente(paciente);
        accesoDatosJDBC.terminarTransaccion();
                
    }
    
}
