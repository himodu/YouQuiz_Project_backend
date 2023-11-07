package LikeLion11th.YouQuiz_Project.domain.study.dto;

public class AnswerSentenceDto {

    private Long student_id;
    private String username;
    private String answer_sentence;

    public AnswerSentenceDto() {
    }

    public AnswerSentenceDto(Long student_id, String username, String answer_sentence) {
        this.student_id = student_id;
        this.username = username;
        this.answer_sentence = answer_sentence;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAnswer_sentence() {
        return answer_sentence;
    }

    public void setAnswer_sentence(String answer_sentence) {
        this.answer_sentence = answer_sentence;
    }
}
