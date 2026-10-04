package com.mycompany.registroalumnos.modelo;

/**
 * Modelo de datos: representa a un alumno registrado.
 */
public class Alumno {

    private final String nombre;
    private final String email;
    private final String genero;
    private final String curso;
    private final int edad;

    public Alumno(String nombre, String email, String genero, String curso, int edad) {
        this.nombre = nombre;
        this.email = email;
        this.genero = genero;
        this.curso = curso;
        this.edad = edad;
    }

    public String getNombre() { return nombre; }
    public String getEmail()  { return email; }
    public String getGenero() { return genero; }
    public String getCurso()  { return curso; }
    public int getEdad()      { return edad; }

    @Override
    public String toString() {
        return nombre + " (" + edad + " años) - " + curso + " - " + genero + " - " + email;
    }
}
