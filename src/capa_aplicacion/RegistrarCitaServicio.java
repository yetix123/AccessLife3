/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa_aplicacion;

import capa_dominio.*;
import capa_persistencia.*;
import capa_exceptions.ExceptionsAll;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author estdi
 */
public class RegistrarCitaServicio {

    private AccesoDatosJDBC accesoDatosJDBC;
    private CitaPostgreSQL citaPostgreSQL;
    private DentistaPostgreSQL dentistaPostgreSQL;
    private PacientePostgreSQL pacientePostgreSQL;

    public RegistrarCitaServicio() {
        accesoDatosJDBC = new AccesoDatosJDBCPostgreSQL();
        citaPostgreSQL = new CitaPostgreSQL(accesoDatosJDBC);
        dentistaPostgreSQL = new DentistaPostgreSQL(accesoDatosJDBC);
        pacientePostgreSQL = new PacientePostgreSQL(accesoDatosJDBC);
    }

    public int incrementarId() throws ExceptionsAll.BaseDeDatosException {
        int id;
        accesoDatosJDBC.abrirConexion();
        id = citaPostgreSQL.idIncrementableCitas();
        accesoDatosJDBC.cerrarConexion();
        return id;
    }

    public Dentista buscarDentista(String nombres) throws Exception {
        accesoDatosJDBC.abrirConexion();
        Dentista dentista = dentistaPostgreSQL.buscarDentistaPorNombres(nombres);
        accesoDatosJDBC.cerrarConexion();
        return dentista;
    }

    public Paciente buscarPaciente(String dni) throws Exception {
        accesoDatosJDBC.abrirConexion();
        Paciente paciente = pacientePostgreSQL.buscarPacientePorDni(dni);
        accesoDatosJDBC.cerrarConexion();
        return paciente;
    }

    public void mostrarTablasDeCitas(DefaultTableModel modelo) throws ExceptionsAll.BaseDeDatosException {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        citaPostgreSQL.mostrarCitasEnMiTabla(modelo);
        accesoDatosJDBC.terminarTransaccion();
    }

    public void cambiarEstado(Cita cita) throws ExceptionsAll.BaseDeDatosException {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        citaPostgreSQL.cambiarEstadoDeMiCita(cita);
        accesoDatosJDBC.terminarTransaccion();
    }

    public Set<String> listaDeHorasDelDentista(Dentista dentista) throws ExceptionsAll.BaseDeDatosException {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        Set<String> horariosDisponibles = citaPostgreSQL.listarHorariosDelDentista(dentista);
        accesoDatosJDBC.terminarTransaccion();
        return horariosDisponibles;
    }

    public int totalCitas(Dentista dentista) throws ExceptionsAll.BaseDeDatosException {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        int totalcitas = citaPostgreSQL.consultarTotalDeCitas(dentista);
        accesoDatosJDBC.terminarTransaccion();
        return totalcitas;
    }

    public boolean pacienteConCitaExistente(Cita cita) throws ExceptionsAll.BaseDeDatosException {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        boolean resultpaciente = citaPostgreSQL.pacienteConCitaExistente(cita);
        accesoDatosJDBC.terminarTransaccion();
        return resultpaciente;
    }

    public boolean dentistaConCitaExistente(Cita cita) throws ExceptionsAll.BaseDeDatosException {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        boolean resultdentista = citaPostgreSQL.dentistaConCitaExistente(cita);
        accesoDatosJDBC.terminarTransaccion();
        return resultdentista;
    }

    public void guardarCita(Cita cita) throws Exception {

        // VALIDAMOS QUE LA FECHA NO SEA ANTES DE LA FECHA ACTUAL
        if (!cita.fechaPasada()) {
            throw new ExceptionsAll.ValidacionException("NO SE PUEDE REGISTRAR UNA CITA CON FECHA PASADA");
        }

        // VALIDAMOS QUE NO SEA UNA FECHA DE FERIADO EN EL CALENDARIO
        if (cita.esFeriado()) {
            throw new ExceptionsAll.ValidacionException("NO HAY CITAS PARA ESA FECHA, ELIJA OTRA FECHA QUE NO SEA FERIADO");
        }

        // VERIFICAMOS EL TOTAL DE CITAS DEL MEDICO
        Dentista dentista = cita.getDentista();
        int totalCitas = totalCitas(dentista);
        if (totalCitas >= 10) {
            throw new ExceptionsAll.ValidacionException("EL MEDICO ALCANZÒ EL LIMITE DE CITAS POR DÌA.");
        }

        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        citaPostgreSQL.guardarCitas(cita);
        accesoDatosJDBC.terminarTransaccion();
    }

}
