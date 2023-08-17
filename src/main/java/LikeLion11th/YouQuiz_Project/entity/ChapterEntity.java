package LikeLion11th.YouQuiz_Project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chapter")
public class ChapterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @JsonIgnore
    @ElementCollection
    @CollectionTable(name="CORRECT_ANSWER_LIST", joinColumns =  @JoinColumn(name="chapter_id", referencedColumnName = "id"))
    private List<Integer> correct_answerList = new ArrayList<>();

    private String youtube_link;
    @JsonIgnore
    @OneToMany(
            targetEntity = Class_ChapterEntity.class,
            mappedBy = "chapterEntity",
            fetch = FetchType.LAZY
    )
    private List<Class_ChapterEntity> class_ChapterEntityList = new ArrayList<>();

    @OneToMany(
            targetEntity = QuizEntity.class,
            mappedBy = "chapterEntity",
            fetch = FetchType.LAZY
    )
    private List<QuizEntity> quizEntityList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Class_ChapterEntity> getClass_ChapterEntityList() {
        return class_ChapterEntityList;
    }

    public void setClass_ChapterEntityList(List<Class_ChapterEntity> class_ChapterEntityList) {
        this.class_ChapterEntityList = class_ChapterEntityList;
    }

    public List<QuizEntity> getQuizEntityList() {
        return quizEntityList;
    }

    public void setQuizEntityList(List<QuizEntity> quizEntityList) {
        this.quizEntityList = quizEntityList;
    }

    @Override
    public String toString() {
        return "ChapterEntity{" +
                "id=" + id +
                ", correct_answerList=" + correct_answerList +
                ", youtube_link='" + youtube_link + '\'' +
                ", class_ChapterEntityList=" + class_ChapterEntityList +
                ", quizEntityList=" + quizEntityList +
                '}';
    }
}
