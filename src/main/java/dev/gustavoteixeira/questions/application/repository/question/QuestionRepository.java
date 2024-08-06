package dev.gustavoteixeira.questions.application.repository.question;

import dev.gustavoteixeira.questions.application.domain.params.Filter;
import dev.gustavoteixeira.questions.application.domain.params.Pagination;
import dev.gustavoteixeira.questions.application.domain.params.Sorting;
import dev.gustavoteixeira.questions.application.domain.question.Question;
import java.util.List;

public interface QuestionRepository {

	Long insert(Question question);

	Question findById(Long id);

	List<Question> findAll(Pagination pagination, Sorting sorting, List<Filter> filters);

	Integer count(List<Filter> filters);
}
