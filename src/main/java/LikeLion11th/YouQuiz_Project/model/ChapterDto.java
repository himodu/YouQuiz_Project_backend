package LikeLion11th.YouQuiz_Project.model;

import LikeLion11th.YouQuiz_Project.entity.Class_ChapterEntity;
import LikeLion11th.YouQuiz_Project.entity.QuizEntity;

import java.util.ArrayList;
import java.util.List;

public class ChapterDto {
    private List<Integer> correct_answerList = new ArrayList<>();
    private String youtube_link;
    private List<QuizEntity> quizEntityList;
    private List<Class_ChapterEntity> class_ChapterEntityList = new ArrayList<>();


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

    public List<Class_ChapterEntity> getClass_ChapterEntityList() {
        return class_ChapterEntityList;
    }

    public void setClass_ChapterEntityList(List<Class_ChapterEntity> class_ChapterEntityList) {
        this.class_ChapterEntityList = class_ChapterEntityList;
    }

    @Override
    public String toString() {
        return "ChapterDto{" +
                ", correct_answerList=" + correct_answerList +
                ", youtube_link='" + youtube_link + '\'' +
                ", quizEntityList=" + quizEntityList +
                ", class_ChapterEntityList=" + class_ChapterEntityList +
                '}';
    }
}
