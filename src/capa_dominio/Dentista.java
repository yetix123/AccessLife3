package capa_dominio;

import java.util.regex.*;

/**
 * @author estdi
 * @version 1.0
 * @created 21-may.-2022 21:58:44
 */
public class Dentista {

    private String dni;
    private String dialaboral;
    private String horario;
    private String estado;
    private String telefono;
    private String email;
    private int iddentista;
    private String nombres;
    private static int totalcitas = 10;

    public Dentista() {
    }

    public Dentista(String nombres, String dni, String dialaboral, String horario, String estado, String telefono, String email) {
        this.nombres = nombres;
        this.dni = dni;
        this.dialaboral = dialaboral;
        this.horario = horario;
        this.estado = estado;
        this.telefono = telefono;
        this.email = email;
    }

    public int getIddentista() {
        return iddentista;
    }

    public void setIddentista(int iddentista) {
        this.iddentista = iddentista;
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

    public String getDialaboral() {
        return dialaboral;
    }

    public void setDialaboral(String dialaboral) {
        this.dialaboral = dialaboral;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
    public int totalCitas() {
        return totalcitas;
    }

}//end Dentista
