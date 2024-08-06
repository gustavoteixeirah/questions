package dev.gustavoteixeira.questions.application.domain.question;

public record Alternative(Long id, Character letter, String description, boolean correct) {}
