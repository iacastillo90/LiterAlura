package com.alura.Literatura.Repositories;

import com.alura.Literatura.Models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :anio AND (a.fechaFallecimiento IS NULL OR a.fechaFallecimiento >= :anio) ORDER BY a.fechaNacimiento ASC")
    List<Autor> findAutorPorAnio(int anio);
}
