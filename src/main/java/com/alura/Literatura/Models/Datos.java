package com.alura.Literatura.Models;

import com.alura.Literatura.DTO.LibroDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos(
        @JsonAlias("results") List<LibroDTO> libros
) {
}