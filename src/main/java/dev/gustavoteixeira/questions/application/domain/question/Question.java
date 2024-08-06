package dev.gustavoteixeira.questions.application.domain.question;

import java.util.List;

public record Question(Long id, String description, List<Alternative> alternatives) {}
