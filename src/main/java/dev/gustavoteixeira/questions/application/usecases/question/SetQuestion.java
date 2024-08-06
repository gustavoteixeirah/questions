package dev.gustavoteixeira.questions.application.usecases.question;

import dev.gustavoteixeira.questions.application.domain.question.Alternative;
import dev.gustavoteixeira.questions.application.domain.question.Question;
import dev.gustavoteixeira.questions.application.repository.question.QuestionRepository;
import dev.gustavoteixeira.questions.application.usecases.UseCase;
import java.util.List;
import java.util.function.Function;

public class SetQuestion extends UseCase<SetQuestion.Input, SetQuestion.Output> {

	private final QuestionRepository repository;

	public SetQuestion(QuestionRepository repository) {
		this.repository = repository;
	}

	@Override
	public Output execute(Input input) {

		var alternatives = input.alternatives().stream().map(toNewAlternative()).toList();

		var question = new Question(null, input.description(), alternatives);

		var id = repository.insert(question);

		return new Output(id);
	}

	private static Function<AlternativeInput, Alternative> toNewAlternative() {
		return alternative ->
				new Alternative(null, alternative.letter(), alternative.description(), alternative.correct());
	}

	public record Input(String description, List<AlternativeInput> alternatives) {}

	public record AlternativeInput(Character letter, String description, boolean correct) {}

	public record Output(Long id) {}
}
