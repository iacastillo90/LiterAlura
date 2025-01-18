package com.alura.Literatura;

import com.alura.Literatura.Principal.Principal;
import com.alura.Literatura.Repositories.AutorRepository;
import com.alura.Literatura.Repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private LibroRepository libroRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository, autorRepository);
		principal.programa();
	}
}
