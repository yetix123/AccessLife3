/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa_exceptions;

/**
 *
 * @author estdi
 */
public class ExceptionsAll {

    private ExceptionsAll() {

    }

    // Excepción para errores en la base de datos
    public static class BaseDeDatosException extends Exception {

        public BaseDeDatosException(String message) {
            super(message);
        }
    }

    // Excepción para errores de validación
    public static class ValidacionException extends Exception {

        public ValidacionException(String message) {
            super(message);
        }
    }

}
