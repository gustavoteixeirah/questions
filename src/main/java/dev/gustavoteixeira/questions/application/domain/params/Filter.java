package dev.gustavoteixeira.questions.application.domain.params;

public record Filter(String fieldName, String fieldValue, Operator operator) {}
