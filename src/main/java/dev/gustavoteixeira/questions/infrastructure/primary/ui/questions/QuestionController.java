package dev.gustavoteixeira.questions.infrastructure.primary.ui.questions;

import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

import dev.gustavoteixeira.questions.application.domain.question.Question;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/questions")
class QuestionController {

	private final Set<Question> questions = new ConcurrentSkipListSet<>(Comparator.comparingLong(Question::id));

	public QuestionController() {
		String[] split = "mathematics, chemistry, biology, portuguese".split(",");
		for (int i = 0; i < split.length; i++) {
			var t = split[i];
			this.questions.add(new Question((long) i, t, null));
		}
	}

	@PostMapping
	HtmxResponse add(@RequestParam("new-question") String description, Model model) {

		this.questions.add(new Question((long) (this.questions.size() + 1), description, null));
		model.addAttribute("questions", this.questions);
		return HtmxResponse.builder()
				.view("questions :: questions")
				.view("questions :: questions-form")
				.build();
	}

	@GetMapping
	String questions(Model model) {
		model.addAttribute("questions", questions);
		return "questions";
	}

	@ResponseBody
	@DeleteMapping(produces = TEXT_HTML_VALUE, path = "/{questionId}")
	String delete(@PathVariable Long questionId) {
		this.questions.stream().filter(q -> q.id().equals(questionId)).forEach(this.questions::remove);
		return "";
	}
}
