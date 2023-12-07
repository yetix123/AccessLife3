/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capa_persistencia;

import capa_dominio.*;
import capa_exceptions.ExceptionsAll;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author estdi
 */
public class CitaPostgreSQL {

    private AccesoDatosJDBC accesoDatosJDBC;

    public CitaPostgreSQL(AccesoDatosJDBC accesoDatosJDBC) {
        this.accesoDatosJDBC = accesoDatosJDBC;
    }

    public int idIncrementableCitas() throws ExceptionsAll.BaseDeDatosException {
        String consultaSQL = "SELECT MAX(idcita) FROM citas;";
        PreparedStatement sentencia;
        int id = 1;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(consultaSQL);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                id = resultado.getInt(1) + 1;
            }
        } catch (Exception e) {
            throw new ExceptionsAll.BaseDeDatosException("ERROR AL OBTENER ULTIMO ID DE CITA." + e.getMessage());
        }
        return id;
    }

    public void guardarCitas(Cita cita) throws ExceptionsAll.BaseDeDatosException {

        String insertSQL = "insert into citas(dentista, paciente, fecha, hora, estado, servicio, detallecita, pagototal)"
                + "values(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(insertSQL);
            sentencia.setInt(1, cita.getDentista().getIddentista());
            sentencia.setInt(2, cita.getPaciente().getIdpaciente());
            sentencia.setString(3, cita.getFecha());
            sentencia.setString(4, cita.getHora());
            sentencia.setString(5, cita.getEstado());
            sentencia.setString(6, cita.getServicio());
            sentencia.setString(7, cita.getDetallecita());
            sentencia.setDouble(8, cita.getPagototal());
            sentencia.executeUpdate();
        } catch (Exception e) {
            throw new ExceptionsAll.BaseDeDatosException("ERROR AL INTENTAR GUARDAR LA CITA." + e.getMessage());
        }
    }

    public int consultarTotalDeCitas(Dentista dentista) throws ExceptionsAll.BaseDeDatosException {
        String consultaSQL = "SELECT COUNT(idcita) AS total FROM citas WHERE dentista = ?";
        PreparedStatement sentencia;
        int totalCitas = 0;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(consultaSQL);
            sentencia.setInt(1, dentista.getIddentista());
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                totalCitas = resultado.getInt("total");
            }
            return totalCitas;
        } catch (Exception e) {
            throw new ExceptionsAll.BaseDeDatosException("ERROR AL INTENTAR CONSULTAR EL TOTAL DE CITAS." + e.getMessage());
        }
    }

    public void mostrarCitasEnMiTabla(DefaultTableModel modelo) throws ExceptionsAll.BaseDeDatosException {

        String mostraSQL = """
                           SELECT ci.idcita, d.nombres AS DENTISTA, pa.nombres AS PACIENTE, ci.fecha, ci.hora, ci.estado, ci.servicio,
                           ci.detallecita, ci.pagototal
                           FROM citas ci JOIN paciente pa ON ci.paciente = pa.idpaciente
                           JOIN dentista d ON ci.dentista = d.iddentista;""";
        PreparedStatement sentencia = null;

        String[] titulos = {"ID", "DENTISTA", "PACIENTE", "FECHA", "HORA", "ESTADO", "SERVICIO", "DETALLE", "PAGO TOTAL"};
        modelo.getDataVector().removeAllElements();
        modelo.setColumnIdentifiers(titulos);

        try {
            sentencia = accesoDatosJDBC.prepararSentencia(mostraSQL);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                String id = resultado.getString("idcita");
                String dentista = resultado.getString("dentista");
                String paciente = resultado.getString("paciente");
                String horario = resultado.getString("fecha");
                String servicio = resultado.getString("hora");
                String fcita = resultado.getString("estado");
                String hora = resultado.getString("servicio");
                String estado = resultado.getString("detallecita");
                String pagot = resultado.getString("pagototal");
                String[] fila = {id, dentista, paciente, horario, servicio, fcita, hora, estado, pagot};
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            throw new ExceptionsAll.BaseDeDatosException("ERROR AL INTENTAR MOSTRAR CITAS." + e.getMessage());
        }
    }

    public boolean pacienteConCitaExistente(Cita cita) throws ExceptionsAll.BaseDeDatosException {

        String querySQL = "SELECT COUNT(*) FROM citas WHERE fecha = ? AND paciente = ?";
        PreparedStatement sentencia;
        try {
//             Verificar si existe un paciente con Cita en esa fecha
            sentencia = accesoDatosJDBC.prepararSentencia(querySQL);
            sentencia.setString(1, cita.getFecha());
            sentencia.setInt(2, cita.getPaciente().getIdpaciente());
            ResultSet rs = sentencia.executeQuery();

            if (rs.next()) {
                int contador = rs.getInt(1);
                if (contador > 0) {
                    throw new ExceptionsAll.BaseDeDatosException("EL PACIENTE YA TIENE UNA CITA RESERVADA.");
                }
                return true;
            }
        } catch (Exception e) {
            throw new ExceptionsAll.BaseDeDatosException("ERROR AL VERIFICAR SI EXISTE UN PACIENTE CON CITA. " + e.getMessage());
        }
        return false;
    }

    public boolean dentistaConCitaExistente(Cita cita) throws ExceptionsAll.BaseDeDatosException {
        String querySQL = "SELECT COUNT(*) FROM citas WHERE fecha = ? AND hora = ? AND dentista = ?";
        PreparedStatement sentencia;

        try {
            // Verificar si el dentista tiene una Cita en esa fecha y hora
            sentencia = accesoDatosJDBC.prepararSentencia(querySQL);
            sentencia.setString(1, cita.getFecha());
            sentencia.setString(2, cita.getHora());
            sentencia.setInt(3, cita.getDentista().getIddentista());

            ResultSet rs = sentencia.executeQuery();

            if (rs.next()) {
                int contador = rs.getInt(1);
                return contador > 0;                
            }
        } catch (Exception e) {
            throw new ExceptionsAll.BaseDeDatosException("ERROR AL VERIFICAR SI EL DENTISTA TIENE UNA CITA: " + e.getMessage());
        }
        return false;
    }

    public void cambiarEstadoDeMiCita(Cita cita) throws ExceptionsAll.BaseDeDatosException {

        String updateSQL = "UPDATE citas SET estado = 'RESERVADO' WHERE hora = ? AND fecha = ?";
        PreparedStatement sentencia;
        try {
//             Cambiar estado en la tabla Cita
            sentencia = accesoDatosJDBC.prepararSentencia(updateSQL);
            sentencia.setString(1, cita.getHora());
            sentencia.setString(2, cita.getFecha());
            sentencia.executeUpdate();

        } catch (Exception e) {
            throw new ExceptionsAll.BaseDeDatosException("ERROR AL CAMBIAR ESTADO. " + e.getMessage());
        }
    }

    public Set<String> listarHorariosDelDentista(Dentista dentista) throws ExceptionsAll.BaseDeDatosException {
        String listaHorariosSQL = "SELECT estado, horario  FROM dentista WHERE nombres = ?";
        PreparedStatement sentencia;
        Set<String> horarios = new TreeSet<>();
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(listaHorariosSQL);
            sentencia.setString(1, dentista.getNombres());
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                String estado = resultado.getString("estado");
                if ("DISPONIBLE".equals(estado)) {
                    horarios.add(resultado.getString("horario"));
                }
            }
            return horarios;
        } catch (Exception e) {
            throw new ExceptionsAll.BaseDeDatosException("ERROR AL INTENTAR OBTENER LOS HORARIOS DEL DENTISTA.  " + e.getMessage());
        }
    }

}
