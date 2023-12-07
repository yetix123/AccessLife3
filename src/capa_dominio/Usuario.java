/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa_dominio;

/**
 *
 * @author estdi
 */
public class Usuario {
    
    private int idusuario;
    private String user;
    private String contrasena;
    private String rol;

    public Usuario() {
    }

    public Usuario(int idusuario, String user, String contraseña, String rol) {
        this.idusuario = idusuario;
        this.user = user;
        this.contrasena = contraseña;
        this.rol = rol;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContraseña() {
        return contrasena;
    }

    public void setContraseña(String contraseña) {
        this.contrasena = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }   
        
}
