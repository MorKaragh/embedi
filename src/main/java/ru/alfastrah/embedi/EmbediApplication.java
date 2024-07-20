package ru.alfastrah.embedi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.Modulithic;

@Modulithic
@SpringBootApplication
public class EmbediApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmbediApplication.class, args);
	}

}
