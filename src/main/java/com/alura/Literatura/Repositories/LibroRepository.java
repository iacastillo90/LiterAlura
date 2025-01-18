package com.alura.Literatura.Repositories;

import com.alura.Literatura.Models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloContaining(String titulo);
    List<Libro> findByIdioma(String idioma);
}
