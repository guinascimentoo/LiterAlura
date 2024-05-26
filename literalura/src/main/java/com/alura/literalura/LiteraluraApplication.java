package com.alura.literalura;

import Principal.Principal;
import Repository.LivroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	private LivroRepository repositorio;

	public static void main(String[] args) {

		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 Principal principal = new Principal(repositorio);
		 principal.exibeMenu();
	}
}