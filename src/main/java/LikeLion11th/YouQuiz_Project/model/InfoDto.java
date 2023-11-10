package LikeLion11th.YouQuiz_Project.model;

import LikeLion11th.YouQuiz_Project.entity.*;

import java.util.ArrayList;
import java.util.List;


// <Return>
// - 문제 및 답 list (문제, 선택지, 정답)
// - 학생들의 주관식 답변 list
// - 교육자의 완료된 답글 list (지금까지 저장했던것만, 답글달았던게 없으면 필요없음)

public class InfoDto {

    // ChapterDto
    private String title;
    private List<Integer> correct_answerList = new ArrayList<>();
    private String youtube_link;
    private List<QuizEntity> quizEntityList;
//    private List<Class_ChapterEntity> class_ChapterEntityList = new ArrayList<>();

    // TeacherDto
    private Long teacher_id;

    // AnswerDto
    private List<AnswerSentenceDto> answer_sentence_list = new ArrayList<>();

    // CommentDto
    private List<CommentListDto> commentEntityList;

    public InfoDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getCorrect_answerList() {
        return correct_answerList;
    }

    public void setCorrect_answerList(List<Integer> correct_answerList) {
        this.correct_answerList = correct_answerList;
    }

    public String getYoutube_link() {
        return youtube_link;
    }

    public void setYoutube_link(String youtube_link) {
        this.youtube_link = youtube_link;
    }

    public List<QuizEntity> getQuizEntityList() {
        return quizEntityList;
    }

    public void setQuizEntityList(List<QuizEntity> quizEntityList) {
        this.quizEntityList = quizEntityList;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public List<AnswerSentenceDto> getAnswer_sentence_list() {
        return answer_sentence_list;
    }

    public void setAnswer_sentence_list(List<AnswerSentenceDto> answer_sentence_list) {
        this.answer_sentence_list = answer_sentence_list;
    }

    public List<CommentListDto> getCommentEntityList() {
        return commentEntityList;
    }

    public void setCommentEntityList(List<CommentListDto> commentEntityList) {
        this.commentEntityList = commentEntityList;
    }
}
