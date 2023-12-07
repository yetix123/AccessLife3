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
public class DentistaTest {
    
    public DentistaTest() {
    }
    
    //VALIDACION DE FORMATO DEL CAMPO NOMBRES
    @Test
    public void test1_ValidarFormatoNombres() {
        System.out.println("EL NOMBRE CUMPLE ");
        Dentista dentista = new Dentista();
        dentista.setNombres("Kenyi Estuard Juarez Ruiz");
        boolean expResult = true;
        boolean result = dentista.validarFormatoNombres();
        assertEquals(result, expResult);
    }
    
    @Test
    public void test2_ValidarFormatoNombres() {
        System.out.println("EL NOMBRE PASO EL LIMITE DE CARACTERES");
        Dentista dentista = new Dentista();
        dentista.setNombres("Kenyi Estuard Juarez RuizZZZZZZZ");
        boolean expResult = false;
        boolean result = dentista.validarFormatoNombres();
        assertEquals(result, expResult);
    }
    
    //------------------------------------------------------------------------------\\
    
    //VALIDACION DE FORMATO DEL CAMPO DNI
    @Test
    public void test1_ValidarFormatoDni() {
        System.out.println("validarFormatoDni");
        Dentista dentista = new Dentista();        
        dentista.setDni("7766554");
        boolean expResult = false;
        boolean result = dentista.validarFormatoDni();
        assertEquals(result, expResult);
    }
    
    //VALIDACION DE FORMATO DEL CAMPO EMAIL
    @Test
    public void test1_ValidarFormatoEmail() {
        System.out.println("validarFormatoEmail");
        Dentista dentista = new Dentista();
        dentista.setEmail("estd");
        boolean expResult = false;
        boolean result = dentista.validarFormatoEmail();
        assertEquals(result, expResult);
    }
    
    //VALIDACION DE FORMATO DEL CAMPO TELEFONO
    @Test
    public void test1_ValidarFormatoTelefono() {
        System.out.println("validarFormatoTelefono");
        Dentista dentista = new Dentista();
        dentista.setTelefono("98765432");
        boolean expResult = false;
        boolean result = dentista.validarFormatoTelefono();
        assertEquals(result, expResult);
    }
    
    //VALIDACION DEL TOTTAL DE CITAS DEL DENTISTA
    @Test
    public void test1_TotalCitas() {
        System.out.println("totalCitas");
        Dentista dentista = new Dentista();
        int expResult = 10;
        int result = dentista.totalCitas();
        assertEquals(result, expResult);
    }
    
}
