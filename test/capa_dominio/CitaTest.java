/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package capa_dominio;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author estdi
 */
public class CitaTest {
    
    public CitaTest() {
    }

    @Test
    public void testFechaPasada() {
        System.out.println("fechaPasada");
        Cita cita = new Cita();
        cita.setFecha("04-12-2023");
        boolean expResult = false;
        boolean result = cita.fechaPasada();
        assertEquals(result, expResult);
    }

    /**
     * Test of esFeriado method, of class Cita.
     */
    @Test
    public void testEsFeriado() {
        System.out.println("esFeriado");
        Cita cita = new Cita();
        cita.setFecha("08-12-2023");
        boolean expResult = true;
        boolean result = cita.esFeriado();
        assertEquals(result, expResult);
    }

    /**
     * Test of tipoServicioDeLaCita method, of class Cita.
     */
//    @Test
//    public void testTipoServicioDeLaCita() {
//        System.out.println("tipoServicioDeLaCita");
//        int cod = 1;
//        Cita Cita = new Cita();
//        Cita.setServicio("CONSULTA");
//        String expResult = "CONSULTA";
//        String result = Cita.tipoServicioDeLaCita(cod);
//        assertEquals(result, expResult);
//    }

    /**
     * Test of precioServicioDeCita method, of class Cita.
     */
//    @Test
//    public void testPrecioServicioDeCita() {
//        System.out.println("precioServicioDeCita");
//        Cita Cita = new Cita();
//        Cita.setServicio("CONSULTA");
//        Cita.setPagototal(50);
//        double expResult = 20;
//        double result = Cita.calcularPrecioServicio();
//        assertEquals(result, expResult, 0.0);
//    }

    /**
     * Test of calcularPagoTotalDeLaCita method, of class Cita.
     */
//    @Test
//    public void testPagoTotalDeLaCita() {
//        System.out.println("pagoTotalDeLaCita");
//        Cita Cita = new Cita();
//        Cita.getPaciente().setEdad(25);
//        Cita.setPagototal(20);
//        double expResult = 20;
//        double result = Cita.pagoTotalDelServicio();
//        assertEquals(result, expResult, 0.0);
//    }

    /**
     * Test of descuentoSiEsMayorDeEdad method, of class Cita.
     */
    
    
}
