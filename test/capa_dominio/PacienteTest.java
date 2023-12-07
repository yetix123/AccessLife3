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
public class PacienteTest {
    
    public PacienteTest() {
    }

    @Test
    public void testValidarFormatoNombres() {
        System.out.println("validarFormatoNombres");
        Paciente paciente = new Paciente();
        paciente.setNombres("Kenyi Estuard juarez Ruiz");
        boolean expResult = true;
        boolean result = paciente.validarFormatoNombres();
        assertEquals(result, expResult);
    }

    @Test
    public void testValidarFormatoDni() {
        System.out.println("validarFormatoDni");
        Paciente paciente = new Paciente();
        paciente.setDni("77564166");
        boolean expResult = true;
        boolean result = paciente.validarFormatoDni();
        assertEquals(result, expResult);
    }

    @Test
    public void testValidarFormatoEmail() {
        System.out.println("validarFormatoEmail");
        Paciente paciente = new Paciente();
        paciente.setEmail("est");
        boolean expResult = false;
        boolean result = paciente.validarFormatoEmail();
        assertEquals(result, expResult);
    }

    @Test
    public void testValidarFormatoTelefono() {
        System.out.println("validarFormatoTelefono");
        Paciente paciente = new Paciente();
        paciente.setTelefono("93524056");
        boolean expResult = false;
        boolean result = paciente.validarFormatoTelefono();
        assertEquals(result, expResult);
    }
    
}
