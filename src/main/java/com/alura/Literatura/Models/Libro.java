package com.alura.Literatura.Models;

import jakarta.persistence.*;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, columnDefinition = "VARCHAR(1000)")
    private String titulo;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private String idioma;
    private double numeroDescargas;

    public Libro() {}

    public Libro(String titulo, Autor autor, String idioma, double numeroDescargas) {
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.numeroDescargas = numeroDescargas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getidioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo + "\n" +
                autor + "\n" +
                "Idioma: " + idioma + "\n" +
                "Numero de descargas: " + numeroDescargas;
    }
}