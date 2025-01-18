package com.alura.Literatura.Principal;

import com.alura.Literatura.DTO.AutorDTO;
import com.alura.Literatura.DTO.LibroDTO;
import com.alura.Literatura.Models.Autor;
import com.alura.Literatura.Models.Datos;
import com.alura.Literatura.Models.Libro;
import com.alura.Literatura.Repositories.AutorRepository;
import com.alura.Literatura.Repositories.LibroRepository;
import com.alura.Literatura.Service.ConsumoAPIService;
import com.alura.Literatura.Service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner lectura = new Scanner(System.in);

    private ConvierteDatos conversor = new ConvierteDatos();
    private ConsumoAPIService consumoApi = new ConsumoAPIService();
    private final String URL_API =  "https://gutendex.com/books/?search=";
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void imprimirOpciones(){
        System.out.println("""
                    ********************************
                    *     Bienvenido a BookMatic   *
                    ********************************
                    1) Buscar libro por titulo.
                    2) Mostrar libros registrados.
                    3) Mostrar autores registrados.
                    4) Mostrar libros por idioma.
                    5) Buscar autores por año.
                    0) Salir.
                    """);
    }

    public void programa(){
        var option = 1;
        while(option != 0){
            try{
                imprimirOpciones();
                System.out.println("¿Qué desea realizar?");
                int opcion = Integer.parseInt(lectura.nextLine());
                if (opcion >= 0 && opcion <= 5){
                    switch(opcion){
                        case 0-> {
                            System.out.println("Finalizando programa.");
                            System.exit(0);
                        }
                        case 1 -> busquedaLibro();
                        case 2 -> listarLibros();
                        case 3 -> listarAutores();
                        case 4 -> listarPorIdioma();
                        case 5 -> listarAutoresPorAnio();
                    }
                }else{
                    System.out.println("Opción incorrecta.");
                }
                System.out.println("\nPresione cualquier tecla para continuar.");
                var continuar = lectura.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void busquedaLibro(){
        System.out.println("Ingrese el titulo del libro: ");
        var nombreLibro = lectura.nextLine();
        Optional<Libro> libroOptional = libroRepository.findByTituloContaining(nombreLibro);
        if(libroOptional.isPresent()){
            System.out.println("********************************");
            System.out.println("*           Resulado           *");
            System.out.println("El libro ya se encuentra registrado.");
            Libro libroBuscadoEnBD = libroOptional.get();
            System.out.println(libroBuscadoEnBD);
        }else {
            var json = consumoApi.obtenerDatos(URL_API+nombreLibro.replace(" ", "%20"));
            var resultado = conversor.obtenerDatos(json, Datos.class);
            Optional<LibroDTO> libro = Optional.ofNullable(resultado.libros().stream()
                    .findFirst().orElse(null));
            if(libro.isPresent()){
                LibroDTO libroGet = libro.get();
                Libro libroPersistir = builderLibro(libroGet);

//              Guardar libro en la base de datos
                libroRepository.save(libroPersistir);
                System.out.println("********************************");
                System.out.println("*           Resulado           *");
                System.out.println(libroPersistir);
            }else {
                System.out.println("No se encontro el libro.");
            }
        }

    }

    public Libro builderLibro(LibroDTO libro){
        Libro libroPersistir = new Libro();
        libroPersistir.setTitulo(libro.titulo());
        libroPersistir.setIdioma(libro.idioma().stream()
                .findFirst().orElse(null));
        libroPersistir.setAutor(builderAutor(libro.autor().stream()
                .findFirst().orElse(null)));
        libroPersistir.setNumeroDescargas(libro.numeroDescarga());
        return libroPersistir;
    }

    public Autor builderAutor(AutorDTO autor){
        Autor autorPersistir = new Autor();
        autorPersistir.setNombre(autor.nombre());
        autorPersistir.setFechaNacimiento(autor.fechaNacimiento());
        autorPersistir.setFechaFallecimiento(autor.fechaFallecimiento());
        return autorPersistir;
    }

    private void listarLibros() {
        List<Libro> libros = (List<Libro>) libroRepository.findAll();
        if(libros.isEmpty()){
            System.out.println("No hay libros registrados.");
        }else {
            libros.stream().forEach(libro -> {
                System.out.println("\n**********Libro**********\n"+libro);
            });
        }
    }

    private void listarAutores() {
        List<Autor> autores = (List<Autor>) autorRepository.findAll();
        if(autores.isEmpty()){
            System.out.println("No hay autores registrados.");
        }else {
            autores.stream().forEach(autor -> {
                System.out.println("\n**********Autor**********\n"+autor);
            });
        }
    }

    private void listarPorIdioma() {
        String idioma = null;
        System.out.println("""
                Seleccione el idioma que desea buscar.
                
                1) Inglés (en)
                2) Español (es)
                3) Francés (fr)
                """);
        int opcion = Integer.parseInt(lectura.nextLine());
        idioma = validarIdioma(opcion);
        List<Libro> libros = (List<Libro>) libroRepository.findByIdioma(idioma);
        if(libros.isEmpty()){
            System.out.println("No hay libros registrados en ese idioma.");
        }else {
            libros.stream().forEach(libro -> {
                System.out.println("\n**********Libro**********\n"+libro);
            });
        }
    }

    public String validarIdioma(int opcion){
        switch(opcion){
            case 1: return "en";
            case 2: return "es";
            case 3: return "fr";
        }
        return null;
    }

    private void listarAutoresPorAnio() {
        System.out.println("Ingrese el año que desea buscar");
        int anio = Integer.parseInt(lectura.nextLine());
        List<Autor> autores = (List<Autor>) autorRepository.findAutorPorAnio(anio);
        if(autores.isEmpty()){
            System.out.println("No hay autores registrados.");
        }else {
            autores.stream().forEach(autor -> {
                System.out.println("\n**********Autor**********\n"+autor);
            });
        }
    }

}