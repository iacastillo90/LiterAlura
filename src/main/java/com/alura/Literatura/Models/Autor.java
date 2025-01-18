package com.alura.Literatura.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor() {
    }

    public Autor(String nombre, Integer fechaNacimiento, Integer fechaFallecimiento, List<Libro> libros) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
        this.libros = libros;
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public void removerLibro(Libro libro) {
        libros.remove(libro);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor: " + nombre + "\n" +
                "Fecha de Nacimiento: " + fechaNacimiento + "\n" +
                "Fecha de Fallecimiento: " + fechaFallecimiento;
    }
}