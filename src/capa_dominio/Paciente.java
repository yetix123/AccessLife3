package capa_dominio;

import java.util.regex.*;

/**
 * @author estdi
 * @version 1.0
 * @created 21-may.-2022 21:58:44
 */
public class Paciente {

    private int idpaciente;
    private String nombres;
    private String dni;
    private int edad;
    private String sexo;
    private String telefono;
    private String email;

    public Paciente() {
    }

    public Paciente(int idpaciente, String nombres, String dni, int edad, String sexo, String telefono, String email) {
        this.idpaciente = idpaciente;
        this.nombres = nombres;
        this.dni = dni;
        this.edad = edad;
        this.sexo = sexo;
        this.telefono = telefono;
        this.email = email;
    }

    public int getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(int idpaciente) {
        this.idpaciente = idpaciente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // VALIDACION DE REGLAS DE NEGOCIO
    //REGLA DE VALIDACION 1
    public boolean validarFormatoNombres() {
        // Verificar si la longitud del nombre es menor o igual a 50 caracteres
        return nombres.length() <= 30;
    }

    //REGLA DE VALIDACION 2
    public boolean validarFormatoDni() {
        // Verificar si el DNI tiene exactamente 8 dígitos
        if (dni.length() == 8) {
            // Verificar si todos los caracteres son dígitos
            for (char c : dni.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false; // Si hay algún carácter no numérico, el DNI no es válido
                }
            }
            return true; // El DNI es válido
        } else {
            return false; // El DNI no tiene 8 dígitos
        }
    }

    //REGLA DE VALIDACION 3
    public boolean validarFormatoEmail() {
        // Utilizar una expresión regular para validar el formato del email
        String regex = ".{1,40}@gmail\\.com";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        // Verificar si la longitud del email no excede los 50 caracteres y si coincide con el patrón
        return email.length() <= 50 && matcher.matches();
    }

    //REGLA DE VALIDACION 4
    public boolean validarFormatoTelefono() {
        // Verificar si el número de teléfono tiene exactamente 9 dígitos
        if (telefono.length() == 9) {
            // Verificar si todos los caracteres son dígitos
            for (char c : telefono.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false; // Si hay algún carácter no numérico, el número no es válido
                }
            }
            return true; // El número de teléfono es válido
        } else {
            return false; // El número de teléfono no tiene 9 dígitos
        }
    }

// REGLAS DE NEGOCIO
// ------- REGLA 1 DE NEGOCIO ---------   
}//end Paciente
