package LikeLion11th.YouQuiz_Project.domain.study.dto;

import java.util.ArrayList;
import java.util.List;

public class AnswerDto {

    private List<Integer> answer_list = new ArrayList<>();
    private String answer_sentence;


    public AnswerDto() {
    }

    public AnswerDto( List<Integer> answersList, String answer_sentence) {

        this.answer_list = answersList;
        this.answer_sentence = answer_sentence;

    }

    public List<Integer> getAnswer_list() {
        return answer_list;
    }

    public void setAnswer_list(List<Integer> answer_list) {
        this.answer_list = answer_list;
    }

    public String getAnswer_sentence() {
        return answer_sentence;
    }

    public void setAnswer_sentence(String answer_sentence) {
        this.answer_sentence = answer_sentence;
    }
}
