package dev.gustavoteixeira.questions;

import org.springframework.boot.SpringApplication;

public class TestQuestionsApplication {

	public static void main(String[] args) {
		SpringApplication.from(Main::main)
				.with(TestcontainersConfiguration.class)
				.run(args);
	}
}
